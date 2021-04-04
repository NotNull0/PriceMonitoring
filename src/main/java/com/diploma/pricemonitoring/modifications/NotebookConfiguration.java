package com.diploma.pricemonitoring.modifications;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NotebookConfiguration {
    public static String getSellerName(Element element) {
        return element.select("div > a").text().split(" ")[0];
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
}
