package com.diploma.pricemonitoring;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.diploma.pricemonitoring.modifications.NotebookConfiguration.getDocument;

public class AutoParser {
    public static void main(String[] args) throws IOException {
        Document document = getDocument("https://ek.ua/ua/APPLE-IPAD-PRO-11-2021-256GB.htm");
        Elements select = document.select("#item-wherebuy-table > tbody > tr");
        for (int i = 0; i < select.size(); i++) {
            String[] onmouseovers = select.get(i).select(">td.where-buy-price > div.hide-blacked > a").attr("onmouseover").split(";")[0].split("\"");
            if (onmouseovers.length>1){
                String onmouseover = onmouseovers[1];
                String href = getDocument(onmouseover).select("a#clickme").attr("href");
                System.out.println(href);
            }
        }
    }
}
