package com.diploma.pricemonitoring.dto.smartphone;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import lombok.Data;

@Data
public class SmartphoneShopDto {
    private final Long id;
    private final Integer price;
    private final Shop shop;

    public SmartphoneShopDto(SmartphonePriceModel model) {
        this.id = model.getId();
        this.price = model.getPrice();
        this.shop = model.getShop();
    }
}
