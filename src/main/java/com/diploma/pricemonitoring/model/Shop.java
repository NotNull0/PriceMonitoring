package com.diploma.pricemonitoring.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public enum Shop {
    ROZETKA("Rozetka.ua"), FOXTROT("FOXTROT.UA"), CACTUS("Cactus"),
    DENIKA("Denika.ua"), MOYO("Moyo.ua"), REPKA("Repka.ua"),
    ALLO("АЛЛО"), EPICENTER("Епіцентр"), COMFY("COMFY.ua"), SHO("ШО"),
    F("F.ua"), ELDORADO("Eldorado.ua"), COMPX("Compx.com.ua"), LUCKYLINK("Luckylink.kiev.ua"),
    ITBOX("Itbox.ua"), NOTEBOOKCHECK("Noteboochek.com.ua"), OPENSHOP("Openshop.ua"), IPHONESALE("iphonesale.com.ua"),
    NIMPHA("Nimpha.ua"), ACDC("ACDC.com.ua"), BRAIN("Brain.com.ua"), AVIC("Avic.ua"), ZENOM("Zenon.com.ua"),
    AMAIN("Amain.com.ua"), ITMAG("iTMag.ua"), QTECHNO("Q-Techno"), STYLUS("Stylus.ua"), CIFRA("Цифра"),
    PULSEPAD("Pulsepad.com.ua"), A_TECHNO("A-techno.com.ua"), VODAFONE("Vodafone.ua"), CITRUS("Citrus.ua"),
    TELEMART("Telemart.ua"), SKAY("Skay.ua"), ENKO("Enko.com.ua"),
    ALIGATOR("Aligator.com.ua"), HOMEBT("Homebt.com.ua"), PALLADIUM("Palladium.ua"),
    ESM("Esm.net.ua"), PROTOVAR("Protovar.com.ua"), SMARTZONE("Smartzone.com.ua"),
    DOMUS("Domus.co.ua"), HOROSHIY("Horoshiy.com.ua"), SMARTS("Smarts.ua"),
    ZHUK("Zhuk.ua"), SHOP_LENOVO("Shop.lenovo.ua"), READER("Reader.ua"),
    TECHNO_POSTAVKA("Tehnopostavka.com.ua"), RINGO("Ringoo.ua"), HAPPY_HOME("Happyhome.net.ua"),
    BIT_MOUSE("Bitmouse.com.ua"), TT("TTT.ua"), UKR_BUY("Ukrbay.com.ua"), GAME_HALL("Gamehall.com.ua"),
    QWERT_SHOP("Qwertyshop.ua"), BUY("Buy.ua"), MY("My"), ION("iOn.ua"), TECHNOKART("Tehnokrat.com.ua"),
    YABLOKI("Yabloki.ua"), EASY_BUY("Easybay.com.ua"), VSETI("Vseti.com.ua"), APPLES("Apple's"), TOILER("Toiler.com.ua"),
    IFUN("Ifun.com.ua"), CHOOSER("Chooser.ua"), OPT_SCLAD("Optsklad.com"), NOTEBOOKER("Notebooker.ua"),
    EXPERT_ONLINE("Expertonline.com.ua"), SENIL("Senil.com.ua"), HIRES("Hi-Res"), SMART_MAG("Smartmag.biz.ua"),
    I_PARTHNER("iPartner.com.ua"), IPODROM("ipodrom.ua"), TECHNO_EZH("Tehnoezh.ua"), I_CASES("iCases.ua"),
    COM_TRADING("Comtrading.ua"), E_DAM("Edam.com.ua"), KTC("Ktc.ua"), SMARTTEL("Smarttel.u"), OPT_SKLAD("Optsklad.com"),
    MAIN_FRAME("Mainframe.com.ua"), YOU_SEE("Yousee.com.ua"), FOXEL("Foxel.com.ua"), EASY_MAC("Easymac.com.ua"),
    TECHNOLOFT("Technoloft.com.ua"), NOFLET("Nofelet.ua"), SMARTTER("Smarttel.ua"), FISHKI("Fishki.ua"),
    NOT_FOUND("NOT_FOUND");

    private final String value;

    Shop(String value) {
        this.value = value;
    }

    public static Shop getShop(String seller) throws FileNotFoundException {
        Shop[] values = Shop.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getValue().equals(seller)) {
                return values[i];
            }
        }
        String filePath = "C:\\Users\\Nazar\\Desktop\\java-project\\pricemonitoring\\src\\main\\resources\\shops";

        try {
            Files.write(Paths.get(filePath), seller.concat("\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
        return Shop.NOT_FOUND;
    }

    public String getValue() {
        return value;
    }
}
