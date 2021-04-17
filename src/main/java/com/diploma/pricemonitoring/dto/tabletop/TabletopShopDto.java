package com.diploma.pricemonitoring.dto.tabletop;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import lombok.Data;

@Data
public class TabletopShopDto {
    private final Long id;
    private final Integer price;
    private final Shop shop;

    public TabletopShopDto(PlanshetPriceModel model) {
        this.id = model.getId();
        this.price = model.getPrice();
        this.shop = model.getShop();
    }
}
