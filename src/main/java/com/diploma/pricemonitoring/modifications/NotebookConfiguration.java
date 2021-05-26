package com.diploma.pricemonitoring.modifications;

import com.diploma.pricemonitoring.model.Shop;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.diploma.pricemonitoring.model.Shop.getShop;

public class NotebookConfiguration {
    public static String getSellerName(Element element) {
        return element.select("div > a").text().split(" ")[0];
    }
    public static String getSellerLink(Element element) {
        return element.select("div > a").attr("href");
    }
    public static String getSellerLinkForNotebook(Element element) {
        return element.select("div > a").attr("onmouseover").split("\"")[1];
    }

    public static String getNotebookDisplay(Document document) {
        return document.select("#group_item_descr > div.m-c-f2 > div:nth-child(1)").text().split(":")[1];
    }

    public static String getNotebookProcessor(Document document) {
        return document.select("#group_item_descr > div.m-c-f2 > div:nth-child(2)").text().split(":")[1];
    }

    public static String getNotebookOZU(Document document) {
        return document.select("#group_item_descr > div.m-c-f2 > div:nth-child(3)").text().split(":")[1];
    }

    public static String getNotebookStorage(Document document) {
        return document.select("#group_item_descr > div.m-c-f2 > div:nth-child(4)").text().split(":")[1];
    }

    public static String getNotebookWeight(Document document) {
        return document.select("#group_item_descr > div.m-c-f2 > div:nth-child(6)").text().split(":")[1];
    }

    public static String getNotebookName(Element element) {
        return element.select("#top-page-title > h1 > b").text();
    }

    public static String getIdProduct(Document document) {
        String regex = "[^\\d\\. ]| \\.|\\.$";
        return document
                .select("body > div > div > div > div.bottom-links-oes > span.rpl.j-wrap.grey > em")
                .attr("jcontent")
                .replaceAll(regex, "")
                .replaceAll(" ", "");
    }

    public static String getIdChildProduct(Document document) {
        String regex = "[^\\d\\. ]| \\.|\\.$";
        return document
                .select("body > div > div > div > div.bottom-links-oes > span.rpl.j-wrap.grey > em")
                .attr("jcontent")
                .split("<br>")[0]
                .replaceAll(regex, "")
                .replaceAll(" ", "");
    }

    public static String getPrice(Element element){
        String regex = "[^\\d\\. ]| \\.|\\.$";
        return element.select("td.conf-prices-price-big > a")
                .text()
                .replaceAll(regex, "")
                .replaceAll(" ", "");
    }

    public static void main(String[] args) throws IOException {
        Document document = getDocument("https://ek.ua/ua/XIAOMI-REDMIBOOK-14-RYZEN-5-8-512GB-VEGA-8.htm");
        Elements shopElements = document.select("#conf_prices_1771265 > div > table > tbody > tr");
        for (int j = 0; j < shopElements.size(); j++) {
            System.out.println("iiiii");
            Shop shop = getShop(getSellerName(shopElements.get(j)));
            Integer price = Integer.valueOf(getPrice(shopElements.get(j)));
            String sellerLink = getSellerLinkForNotebook(shopElements.get(j));
            System.out.println(shop);
            System.out.println(price);
            System.out.println(sellerLink);
        }
    }

    private static Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36")
                .referrer("http://www.google.com")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .maxBodySize(0)
                .timeout(25000)
                .get();
    }

}
