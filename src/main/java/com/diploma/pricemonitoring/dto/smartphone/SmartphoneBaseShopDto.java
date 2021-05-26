package com.diploma.pricemonitoring.dto.smartphone;

import lombok.Data;

import java.util.List;

@Data
public class SmartphoneBaseShopDto {
    private SmartphoneBaseDto details;
    private List<SmartphoneShopDto> list;

    public SmartphoneBaseShopDto(SmartphoneBaseDto details, List<SmartphoneShopDto> list) {
        this.details = details;
        this.list = list;
    }
}
