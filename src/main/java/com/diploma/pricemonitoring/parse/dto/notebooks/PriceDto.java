package com.diploma.pricemonitoring.parse.dto.notebooks;

import com.diploma.pricemonitoring.model.Shop;

public class PriceDto {
    private Integer price;
    private Shop shop;
    private String sellerLink;

    public String getSellerLink() {
        return sellerLink;
    }

    public PriceDto setSellerLink(String sellerLink) {
        this.sellerLink = sellerLink;
        return this;
    }

    public PriceDto(Integer price, Shop shop) {
        this.price = price;
        this.shop = shop;
    }

    public Integer getPrice() {
        return price;
    }

    public PriceDto setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public PriceDto setShop(Shop shop) {
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
