package com.diploma.pricemonitoring.dto.tabletop;

import lombok.Data;

import java.util.List;

@Data
public class TabletopBaseShopDto {
    private TabletopBaseDto details;
    private List<TabletopShopDto> list;

    public TabletopBaseShopDto(TabletopBaseDto details, List<TabletopShopDto> list) {
        this.details = details;
        this.list = list;
    }
}
