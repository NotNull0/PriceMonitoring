package com.diploma.pricemonitoring.service.notebook;

import com.diploma.pricemonitoring.model.notebook.NotebookShopPriceModel;
import com.diploma.pricemonitoring.repository.notebook.NotebookShopPriceRepository;
import com.diploma.pricemonitoring.service.notebook.interf.ShopPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopPriceServiceImpl implements ShopPriceService {
    @Autowired
    private NotebookShopPriceRepository notebookShopPriceRepository;

    @Override
    public NotebookShopPriceModel save(NotebookShopPriceModel notebookShopPriceModel) {
        return notebookShopPriceRepository.save(notebookShopPriceModel);
    }
}
