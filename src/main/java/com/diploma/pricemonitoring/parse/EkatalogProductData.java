package com.diploma.pricemonitoring.parse;

import com.diploma.pricemonitoring.modifications.NotebookConfiguration;
import com.diploma.pricemonitoring.parse.dto.notebooks.NotebookDto;
import com.diploma.pricemonitoring.parse.dto.notebooks.NotebookPriceDto;
import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.service.interf.NotebookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.diploma.pricemonitoring.modifications.NotebookConfiguration.*;
import static com.diploma.pricemonitoring.model.Shop.getShop;
import static com.diploma.pricemonitoring.utils.TextColor.*;

@Service
public class EkatalogProductData implements ProductData {

    private final String BASE_SITE_URL = "https://ek.ua";
    private final String NOTEBOOK_START_PAGE = "https://ek.ua/ua/list/298/";
    @Autowired
    private NotebookService notebookService;

    @Override
    public Set getProducts(ProductType productType) throws IOException {
        if (ProductType.NOTEBOOK.equals(productType)) {
            return new Notebooks().getNotebooksData();
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


    private class Notebooks {
        private final String  configTableSelectorFormat = "#con-%s > div > table > tbody > tr > td.conf-td.conf-price-link-close > a";
        private final String  descriptionSelectorFormat = "#item_bl_%s > div.conf-desc-ai-title";

        public Notebooks() {
        }

        public Set<NotebookDto> getNotebooksData() throws IOException {
            List<String> notebooksURL = List.copyOf(getAllNotebookURL());
            List<NotebookDto> notebooks = new LinkedList<>();
            for (int i = 0; i < notebooksURL.size(); i++) {
                System.out.println(ANSI_BLUE + "Парсинг ноутбука -->" + notebooksURL.get(i));
                Document document = getDocument(notebooksURL.get(i));
                String idProduct = NotebookConfiguration.getIdProduct(document);

                String configurationNotebookTableSelector = String.format(configTableSelectorFormat, idProduct);
                String descriptionSelector = String.format(descriptionSelectorFormat, idProduct);

                Elements configurationTableElements = document.select(configurationNotebookTableSelector);
                String description = document.select(descriptionSelector).text();

                List<String> otherConfigurationListLink = getOtherConfigurationListLink(configurationTableElements);


                for (int y = 0; y < otherConfigurationListLink.size(); y++) {
                    System.out.println(ANSI_BLUE + "КОНФІГУРАЦІЯ ---> " + y);
                    Document notebookConfigurationDocument = getDocument(otherConfigurationListLink.get(y));
                    NotebookDto notebookConfiguration = new NotebookDto(notebookConfigurationDocument);
                    notebookConfiguration.setDescription(description);
                    System.out.println(ANSI_BLUE + "Назва -----> " + notebookConfiguration.getName());
                    String idChildProduct = getIdChildProduct(notebookConfigurationDocument);
                    String imageSelector = String.format("#img_200_%s", idChildProduct);
                    String selectorMarket = String.format("#conf_prices_%s > div > table > tbody > tr", idChildProduct);
                    String imageURL = notebookConfigurationDocument.select(imageSelector).attr("src");
                    notebookConfiguration.setImageURL(imageURL);
                    Elements shopElements = notebookConfigurationDocument.select(selectorMarket);

                    List<NotebookPriceDto> notebookPriceDtos = new LinkedList<>();
                    for (int j = 0; j < shopElements.size(); j++) {
                        Shop shop = getShop(getSellerName(shopElements.get(j)));
                        Integer price = Integer.valueOf(getPrice(shopElements.get(j)));
                        System.out.println(ANSI_BLUE + "МАГАЗИН ---> " + j + " " + shop.toString() + " [Ціна: " + price + "]");
                        NotebookPriceDto notebookPriceDto = new NotebookPriceDto(price, shop);
                        notebookPriceDtos.add(notebookPriceDto);
                    }
                    notebookConfiguration.setPriceNotebooks(notebookPriceDtos);
                    notebookService.save(notebookConfiguration);
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
            System.out.println(ANSI_GREEN + "Отримання посилань на товар ");
            System.out.println(ANSI_GREEN + "Загальна кількість сторінок --->" + countPage);
            for (int i = 0; i <= countPage; i++) {
                System.out.println(ANSI_GREEN + "Сторінка №" + i);
                String nextPageURL = String.format(NOTEBOOK_START_PAGE + "/%s/", i);
                Document document1 = getDocument(nextPageURL);
                List<String> mainPageNotebooksFromPage = getMainPageNotebooksFromPage(document1);
                URLs.addAll(mainPageNotebooksFromPage);
            }
            return URLs;
        }

        private Integer getCountPage(Document document) {
            try {
                return Integer.valueOf(document.select("body > div.common-table-div.s-width > table > tbody > tr > td.main-part-content > div.list-pager-div > div > div > a:last-child").text());
            } catch (Exception e) {
                System.out.println(ANSI_RED + "PAGER_NOT_FOUND" + ANSI_RESET);
                return 0;
            }
        }

    }
}
