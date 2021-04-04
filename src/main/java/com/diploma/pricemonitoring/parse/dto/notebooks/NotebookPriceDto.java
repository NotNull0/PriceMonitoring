package com.diploma.pricemonitoring.parse.dto.notebooks;

import com.diploma.pricemonitoring.model.Shop;

public class NotebookPriceDto {
    private Integer price;
    private Shop shop;

    public NotebookPriceDto(Integer price, Shop shop) {
        this.price = price;
        this.shop = shop;
    }

    public Integer getPrice() {
        return price;
    }

    public NotebookPriceDto setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public NotebookPriceDto setShop(Shop shop) {
        this.shop = shop;
        return this;
    }

    @Override
    public String toString() {
        return "PriceNotebook{" +
                "price=" + price +
                ", shop=" + shop +
                '}';
    }
}
