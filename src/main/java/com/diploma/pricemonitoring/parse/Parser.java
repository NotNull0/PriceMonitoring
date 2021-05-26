package com.diploma.pricemonitoring.parse;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.modifications.NotebookConfiguration;
import com.diploma.pricemonitoring.modifications.SmartphoneConfiguration;
import com.diploma.pricemonitoring.parse.dto.notebooks.NotebookDto;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;
import com.diploma.pricemonitoring.parse.dto.smartphones.SmartphoneDto;
import com.diploma.pricemonitoring.parse.dto.tabletop.TabletopDto;
import com.diploma.pricemonitoring.service.notebook.interf.NotebookService;
import com.diploma.pricemonitoring.service.smartphone.interf.SmartphoneService;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.diploma.pricemonitoring.model.Shop.getShop;
import static com.diploma.pricemonitoring.modifications.NotebookConfiguration.*;
import static com.diploma.pricemonitoring.utils.TextColor.*;

@Slf4j
@Service
public class Parser implements ProductData {

    private final String BASE_SITE_URL = "https://ek.ua";
    private final String NOTEBOOK_START_PAGE = "https://ek.ua/ua/list/298/";
    @Autowired
    private NotebookService notebookService;
    @Autowired
    private SmartphoneService smartphoneService;
    @Autowired
    private TabletopService tabletopService;

    @Override
    public Set getProducts(ProductType productType) throws IOException {
        if (ProductType.NOTEBOOK.equals(productType)) {
            return new Notebooks().getNotebooksData();
        } else if (ProductType.SMART_PHONE.equals(productType)) {
            return new Smartphone().getData();
        } else if (ProductType.TABLETOP.equals(productType)) {
            return new Tabletop().getData();
        }
        return Collections.EMPTY_SET;
    }

    private Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36")
                .referrer("http://www.google.com")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .maxBodySize(0)
                .timeout(25000)
                .get();
    }

    private Integer getCountPage(Document document) {
        try {
            return Integer.valueOf(document.select("body > div.common-table-div.s-width > table > tbody > tr > td.main-part-content > div.list-pager-div > div > div > a:last-child").text());
        } catch (Exception e) {
            log.info(ANSI_RED + "PAGER_NOT_FOUND" + ANSI_RESET);
            return 0;
        }
    }

    private class Notebooks {
        private final String configTableSelectorFormat = "#con-%s > div > table > tbody > tr > td.conf-td.conf-price-link-close > a";
        private final String descriptionSelectorFormat = "#item_bl_%s > div.conf-desc-ai-title";

        public Notebooks() {
        }

        public Set<NotebookDto> getNotebooksData() throws IOException {
            List<String> notebooksURL = List.copyOf(getAllNotebookURL());
            List<NotebookDto> notebooks = new LinkedList<>();
            for (int i = 0; i < notebooksURL.size(); i++) {
                Document document = getDocument(notebooksURL.get(i));
                String idProduct = NotebookConfiguration.getIdProduct(document);

                String configurationNotebookTableSelector = String.format(configTableSelectorFormat, idProduct);
                String descriptionSelector = String.format(descriptionSelectorFormat, idProduct);

                Elements configurationTableElements = document.select(configurationNotebookTableSelector);
                String description = document.select(descriptionSelector).text();

                List<String> otherConfigurationListLink = getOtherConfigurationListLink(configurationTableElements);


                for (int y = 0; y < otherConfigurationListLink.size(); y++) {
                    log.info(ANSI_BLUE + "КОНФІГУРАЦІЯ ---> " + y);
                    Document notebookConfigurationDocument = getDocument(otherConfigurationListLink.get(y));
                    NotebookDto notebookConfiguration = new NotebookDto(notebookConfigurationDocument);
                    notebookConfiguration.setDescription(description);
                    log.info(ANSI_BLUE + "Назва -----> " + notebookConfiguration.getName());
                    String idChildProduct = getIdChildProduct(notebookConfigurationDocument);
                    String imageSelector = String.format("#img_200_%s", idChildProduct);
                    String selectorMarket = String.format("#conf_prices_%s > div > table > tbody > tr", idChildProduct);

                    String imageURL = notebookConfigurationDocument.select(imageSelector).attr("src");
                    notebookConfiguration.setImageURL(imageURL);
                    Elements shopElements = notebookConfigurationDocument.select(selectorMarket);

                    List<PriceDto> priceDtos = new LinkedList<>();
                    for (int j = 0; j < shopElements.size(); j++) {
                        Shop shop = getShop(getSellerName(shopElements.get(j)));
                        Integer price = Integer.valueOf(getPrice(shopElements.get(j)));
                        String sellerLink = getSellerLinkForNotebook(shopElements.get(j));
                        log.info(ANSI_BLUE + "МАГАЗИН ---> " + j + " " + shop.toString() + " [Ціна: " + price + "]");
                        PriceDto priceDto = new PriceDto(price, shop);
                        priceDto.setSellerLink(sellerLink);
                        priceDtos.add(priceDto);
                    }
                    notebookConfiguration.setPriceNotebooks(priceDtos);
                    notebookService.save(notebookConfiguration);
                    log.info(notebookConfiguration.toString());
                    notebooks.add(notebookConfiguration);
                }
            }
            return Set.copyOf(notebooks);
        }

        private List<String> getOtherConfigurationListLink(Elements configurationTableElements) {
            List<String> otherNotebookConfigurationLink = new ArrayList<>();
            for (int x = 0; x < configurationTableElements.size(); x++) {
                String name = configurationTableElements.get(x).text().replaceAll("\\d", "");
                if (name.equals("Порівняти ціни ")) {
                    String URL = BASE_SITE_URL + configurationTableElements.get(x).attr("link");
                    otherNotebookConfigurationLink.add(URL);
                }
            }
            return otherNotebookConfigurationLink;
        }

        private List<String> getMainPageNotebooksFromPage(Document document) {
            List<String> URLs = new LinkedList<>();
            Elements elements = document.select("#list_form1 > div > table > tbody > tr > td.model-short-info > table > tbody > tr > td.model-conf-title > a");
            for (int i = 0; i < elements.size(); i++) {
                String URL = BASE_SITE_URL + elements.get(i).attr("href");
                URLs.add(URL);
            }
            return URLs;
        }

        private Set<String> getAllNotebookURL() throws IOException {
            Document document = getDocument(NOTEBOOK_START_PAGE);
            int countPage = getCountPage(document);
            Set<String> URLs = new HashSet<>();
            log.info(ANSI_GREEN + "Отримання посилань на товар ");
            log.info(ANSI_GREEN + "Загальна кількість сторінок --->" + countPage);
            for (int i = 0; i <= countPage; i++) {
                log.info(ANSI_GREEN + "Сторінка №" + i);
                String nextPageURL = String.format(NOTEBOOK_START_PAGE + "/%s/", i);
                Document document1 = getDocument(nextPageURL);
                List<String> mainPageNotebooksFromPage = getMainPageNotebooksFromPage(document1);
                URLs.addAll(mainPageNotebooksFromPage);
            }
            return URLs;
        }


    }

    private class Tabletop {
        private final String TABLETOP_START_PAGE = "https://ek.ua/ua/list/30/";
        private final String LINK_SELECTOR = "#list_form1 > div > div > table > tbody > tr > td.model-short-info > table > tbody > tr > td > a";
        private int countPages;

        public Tabletop() throws IOException {
            Document firstPage = getDocument(TABLETOP_START_PAGE);
            this.countPages = getCountPage(firstPage);
            log.info(String.valueOf(countPages));
        }

        private Set getData() throws IOException {
            List<String> linksOnSmartphonePage = new LinkedList<>(getLinksOnTabletopPage());
            for (int i = 0; i < linksOnSmartphonePage.size(); i++) {
                log.info(linksOnSmartphonePage.get(i));
                Document document = getDocument(linksOnSmartphonePage.get(i));
                TabletopDto tabletopDto = new TabletopDto(document);
                tabletopDto.populate(document, tabletopDto);
                String linkToMoreShops = BASE_SITE_URL + document.select(String.format("#item_sm_wb_%s > a", tabletopDto.getIdProduct())).attr("link");
                log.info(tabletopDto.toString());

                Document document1 = getDocument(linkToMoreShops);
                Elements select = document.select("#item-wherebuy-table > tbody > tr");
                List<PriceDto> marketList = new LinkedList<>();

                for (int y = 0; y < select.size(); y++) {
                    log.info("NUMBER" + y);
                    String marget;
                    Integer priceProduct;
                    String price = select.get(y).select(">td.where-buy-price").text().replaceAll("[^0-9]", "");
                    String subMarket = select.get(y).select(">td.where-buy-description > div.hide-blacked > span.it-marketplace.text-nowrap.ib").text();
                    String mainMarket = select.get(y).select(">td.where-buy-description > div.hide-blacked > a").text();
                    String onmouseover = select.get(y).select(">td.where-buy-price > div.hide-blacked > a").attr("onmouseover");
                    String sellerLink = "empty";
                    if (!onmouseover.isBlank()){
                        sellerLink = select.get(y).select(">td.where-buy-price > div.hide-blacked > a").attr("onmouseover").split(";")[0].split("\"")[1];
                    }
                    if (!price.isEmpty()) {
                        priceProduct = Integer.valueOf(price);
                        if (!subMarket.isEmpty()) {
                            marget = subMarket.split(" ")[2];
                        } else {
                            marget = mainMarket;
                        }
                        Shop shop = getShop(marget);
                        PriceDto priceDto = new PriceDto(priceProduct, shop);
                        priceDto.setSellerLink(sellerLink);
                        marketList.add(priceDto);
                    }
                }
                tabletopDto.setPriceDto(marketList);
                if (tabletopDto.getPriceDto().size() > 0) {
                    log.info(tabletopDto.toString());
                    tabletopService.save(tabletopDto);
                }
            }
            return Collections.EMPTY_SET;
        }

        private Set<String> getLinksOnTabletopPage() throws IOException {
            log.info("START");
            Set<String> pageURLs = new HashSet<>();
            for (int i = 0; i < this.countPages; i++) {
                log.info("ITERATION # " + i);
                Document currentPage = getDocument(TABLETOP_START_PAGE + i);
                List<String> strings = currentPage.select(LINK_SELECTOR).eachAttr("href");
                List<String> collect = strings.stream().filter(s -> s.startsWith("/ua")).map(s -> BASE_SITE_URL + s).collect(Collectors.toList());
                pageURLs.addAll(collect);
            }
            return pageURLs;
        }


    }

    private class Smartphone {
        private final String SMARTPHONE_START_PAGE = "https://ek.ua/ua/list/122/";
        private final String LINK_SELECTOR = "#list_form1 > div > div > table > tbody > tr > td.model-short-info > table > tbody > tr > td > a";
        private int countPages;

        public Smartphone() throws IOException {
            Document firstPage = getDocument(SMARTPHONE_START_PAGE);
            this.countPages = getCountPage(firstPage);
            log.info(String.valueOf(countPages));
        }

        private Set getData() throws IOException {
            List<String> linksOnSmartphonePage = new LinkedList<>(getLinksOnSmartphonePage());
            for (int i = 0; i < linksOnSmartphonePage.size(); i++) {
                Document document = getDocument(linksOnSmartphonePage.get(i));
                SmartphoneDto smartphoneDto = new SmartphoneDto(document);
                String idProduct = getIdProduct(document);
                SmartphoneConfiguration.populateData(smartphoneDto, document, idProduct);
                String allPricesURL = BASE_SITE_URL + SmartphoneConfiguration.getAllPricesURL2(idProduct, document);
                Document shops = getDocument(allPricesURL);
                //ЯКІ МАГАЗИНИ ПРОДАЮТЬ
                Elements select = document.select("#item-wherebuy-table > tbody > tr");
                List<PriceDto> marketList = new LinkedList<>();
                for (int y = 0; y < select.size(); y++) {
                    log.info("NUMBER" + y);
                    String marget;
                    Integer priceProduct;
                    String price = select.get(y).select(">td.where-buy-price").text().replaceAll("[^0-9]", "");
                    String subMarket = select.get(y).select(">td.where-buy-description > div.hide-blacked > span.it-marketplace.text-nowrap.ib").text();
                    String mainMarket = select.get(y).select(">td.where-buy-description > div.hide-blacked > a").text();
                    String onmouseover = select.get(y).select(">td.where-buy-price > div.hide-blacked > a").attr("onmouseover");
                    String sellerLink = "empty";
                    if (!onmouseover.isBlank()){
                        sellerLink = select.get(y).select(">td.where-buy-price > div.hide-blacked > a").attr("onmouseover").split(";")[0].split("\"")[1];
                    }
                    if (!price.isEmpty()) {
                        priceProduct = Integer.valueOf(price);
                        if (!subMarket.isEmpty()) {
                            marget = subMarket.split(" ")[2];
                        } else {
                            marget = mainMarket;
                        }
                        Shop shop = getShop(marget);
                        PriceDto priceDto = new PriceDto(priceProduct, shop);
                        priceDto.setSellerLink(sellerLink);
                        marketList.add(priceDto);
                    }
                }
                smartphoneDto.setPriceDto(marketList);
                smartphoneDto.marketToString();
                if (smartphoneDto.getPriceDto().size() > 0) {
                    log.info(smartphoneDto.toString());
                    smartphoneService.save(smartphoneDto);
                }
            }
            return Collections.EMPTY_SET;
        }

        private Set<String> getLinksOnSmartphonePage() throws IOException {
            log.info("START");
            Set<String> pageURLs = new HashSet<>();
            for (int i = 0; i < this.countPages; i++) {
                log.info("ITERATION # " + i);
                Document currentPage = getDocument(SMARTPHONE_START_PAGE + i);
                List<String> strings = currentPage.select(LINK_SELECTOR).eachAttr("href");
                List<String> collect = strings.stream().filter(s -> s.startsWith("/ua")).map(s -> BASE_SITE_URL + s).collect(Collectors.toList());
                pageURLs.addAll(collect);
            }
            return pageURLs;
        }

    }

}
