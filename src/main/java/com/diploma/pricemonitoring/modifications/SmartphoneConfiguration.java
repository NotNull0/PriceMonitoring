package com.diploma.pricemonitoring.modifications;


import com.diploma.pricemonitoring.parse.dto.smartphones.SmartphoneDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SmartphoneConfiguration {

    public static String getName(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div.cont-block-title.no-mobile > span", id)).text();
    }

    public static String getDescription(Document document) {
        return document.select("#eski-i-other-txt-1 > div.desc-ai-title").text();
    }

    public static String getImageURl(String id, Document document) {
        return document.select(String.format("#img_200_%s", id)).attr("src");
    }
    public static String getAllPricesURL(String id, Document document) {
        return document.select(String.format("#item_bl__%s > div.desc-wbuy > div.cont-block-title.or.cont-block-line-new > a", id)).attr("link");
    }
    public static String getAllPricesURL2(String id, Document document) {
        return document.select(String.format("#item_sm_wb_%s > a", id)).attr("link");
    }


    public static SmartphoneDto populateData(SmartphoneDto dto, Document document, String id) {
        Elements select = document.select(String.format("#item_bl_%s > div > div > div.m-c-f2 > div.m-s-f3", id));
        for (int i = 0; i < select.size(); i++) {
            String[] split = select.get(i).text().split(":");
            String s1 = split[0];
            switch (s1) {
                case "Екран":
                    dto.setDisplay(split[1]);
                    break;
                case "Ємність батареї":
                    dto.setBattery(split[1]);
                    break;
                case "Вага":
                    dto.setWeight(split[1]);
                    break;
                case "Процесор":
                    dto.setProcessor(split[1]);
                    break;
                case "Камера":
                    dto.setCamera(split[1]);
                    break;
                case "Відео":
                    dto.setVideo(split[1]);
                    break;
                case "Пам'ять":
                    dto.setMemory(split[1]);
                    break;
            }
        }
        return dto;
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://ek.ua/ua/prices/maxcom-mm134/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36")
                .referrer("http://www.google.com")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .maxBodySize(0)
                .timeout(25000)
                .get();
        Elements select = document.select("#item-wherebuy-table > tbody > tr");
        System.out.println(select.size());
        for (int i = 0; i < select.size(); i++) {
            String text = select.get(i).select(">td.where-buy-price").text().split("\\.")[0].split(" ")[0];
            String string = select.get(i).select(">td.where-buy-description > div.hide-blacked > span.it-marketplace.text-nowrap.ib").text();
            String string2 = select.get(i).select(">td.where-buy-description > div.hide-blacked > a").text();
            System.out.println(text);
            if (string.equals("")){
                System.out.println(string2);
            }else {
                System.out.println(string.split(" ")[2]);
            }
        }
        /////////////////////////////////////////////#item_bl_1133213 > div:nth-child(2) > div:nth-child(4) > div > div:nth-child(4)
    }
}
