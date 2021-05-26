package com.diploma.pricemonitoring.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotebookBaseShopDto {
    private NotebookBaseDto details;
    private List<NotebookShopDto> list;

    public NotebookBaseShopDto(NotebookBaseDto details, List<NotebookShopDto> list) {
        this.details = details;
        this.list = list;
    }
}
