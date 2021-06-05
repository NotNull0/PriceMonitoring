package com.diploma.pricemonitoring.modifications;

import com.diploma.pricemonitoring.parse.dto.tabletop.TabletopDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TabletopConfiguration {

    public static String getSystem(String id, Document document) {
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    String text = document.select(String.format("#item_bl_%s > div:nth-child(%s) > div:nth-child(%s) > div > div:nth-child(%s)", id, i, j, k)).text();
                    System.out.println(text);

                }
            }
        }
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(1)", id)).text().split(":")[1];
    }

    public static String getDisplayDiagonal(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(2)", id)).text().split(":")[1];
    }

    public static String getDisplayResolution(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(3)", id)).text().split(":")[1];
    }

    public static String getProcessor(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(4)", id)).text().split(":")[1];
    }

    public static String getStorage(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(5)", id)).text().split(":")[1];
    }

    public static String getFrontCamera(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(6)", id)).text().split(":")[1];
    }

    public static String getBattery(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(7)", id)).text().split(":")[1];
    }

    public static String getMaterial(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(7)", id)).text().split(":")[1];
    }

    public static String getWeight(String id, Document document) {
        return document.select(String.format("#item_bl_%s > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(7)", id)).text().split(":")[1];
    }

    public static String getImageURl(String id, Document document) {
        return document.select(String.format("#img_200_%s", id)).attr("src");
    }

    public static String getDescription(Document document) {
        return document.select("#eski-i-other-txt-1 > div.desc-ai-title").text();
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://ek.ua/ua/HUAWEI-HONOR-PAD-V6.htm")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.190 Safari/537.36")
                .referrer("http://www.google.com")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .maxBodySize(0)
                .timeout(25000)
                .get();

        String s = document.select("#item_bl_1875379 > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(1)").text().split(":")[1];
        System.out.println(s);
    }

    public static void populate(Document document, TabletopDto tabletopDto, String idNumber) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    String text = document.select(String.format("#item_bl_%s > div:nth-child(%s) > div:nth-child(%s) > div > div:nth-child(%s)", idNumber, i, j, k)).text();
                    if (text.startsWith("Роздільна здатність")){
                        tabletopDto.setDisplayResolution(text.split(":")[1]);
                    }else if (text.startsWith("Процесор")){
                        tabletopDto.setProcessor(text.split(":")[1]);
                    }else if (text.startsWith("Вбудована пам'ять")){
                        tabletopDto.setStorage(text.split(":")[1]);
                    }else if (text.startsWith("Передня камера")){
                        tabletopDto.setFrontCamera(text.split(":")[1]);
                    }else if (text.startsWith("Ємність батареї")){
                        tabletopDto.setBattery(text.split(":")[1]);
                    }else if (text.startsWith("Матеріал корпуса")){
                        tabletopDto.setMaterial(text.split(":")[1]);
                    }else if (text.startsWith("Вага")){
                        tabletopDto.setWeight(text.split(":")[1]);
                    }else if (text.startsWith("Діагональ дисплея")){
                        tabletopDto.setDisplayDiagonal(text.split(":")[1]);
                    }else if (text.startsWith("Система")){
                        tabletopDto.setSystem(text.split(":")[1]);
                    }

                }
            }
        }
    }
}
