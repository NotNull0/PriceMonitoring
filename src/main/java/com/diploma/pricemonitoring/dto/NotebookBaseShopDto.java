package com.diploma.pricemonitoring.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotebookBaseShopDto {
    private NotebookBaseDto notebookBaseDto;
    private List<NotebookShopDto> list;

    public NotebookBaseShopDto(NotebookBaseDto notebookBaseDto, List<NotebookShopDto> list) {
        this.notebookBaseDto = notebookBaseDto;
        this.list = list;
    }
}
