package com.diploma.pricemonitoring.dto;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.model.notebook.NotebookPriceModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import lombok.Data;

@Data
public class NotebookShopDto {
    private final Long id;
    private final Integer price;
    private final Shop shop;
    private String sellerLink;

    public NotebookShopDto(NotebookPriceModel model) {
        this.id = model.getId();
        this.price = model.getPrice();
        this.shop = model.getShop();
        this.sellerLink = model.getSellerLink();
    }
}
