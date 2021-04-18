package com.diploma.pricemonitoring.dto.tabletop;

import lombok.Data;

import java.util.List;

@Data
public class TabletopBaseShopDto {
    private TabletopBaseDto tabletopBaseDto;
    private List<TabletopShopDto> list;

    public TabletopBaseShopDto(TabletopBaseDto tabletopBaseDto, List<TabletopShopDto> list) {
        this.tabletopBaseDto = tabletopBaseDto;
        this.list = list;
    }
}
