package com.diploma.pricemonitoring.dto.smartphone;

import lombok.Data;

import java.util.List;

@Data
public class SmartphoneBaseShopDto {
    private SmartphoneBaseDto smartphoneBaseDto;
    private List<SmartphoneShopDto> list;

    public SmartphoneBaseShopDto(SmartphoneBaseDto smartphoneBaseDto, List<SmartphoneShopDto> list) {
        this.smartphoneBaseDto = smartphoneBaseDto;
        this.list = list;
    }
}
