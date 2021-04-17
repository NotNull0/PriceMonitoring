package com.diploma.pricemonitoring.service.notebook.interf;

import com.diploma.pricemonitoring.dto.NotebookShopDto;
import com.diploma.pricemonitoring.model.notebook.NotebookPriceModel;

import java.util.List;

public interface NotebookPriceService {
    NotebookPriceModel save(NotebookPriceModel model);

    List<NotebookPriceModel> getShopsByNotebookId(Long id);

    List<NotebookShopDto> getShopsByNotebookIdDto(Long id);

}
