package com.diploma.pricemonitoring.service.smartphone;

import com.diploma.pricemonitoring.model.smartphone.SmartphoneShopPriceModel;
import com.diploma.pricemonitoring.repository.smartphone.SmartphoneShopPriceRepository;
import com.diploma.pricemonitoring.service.smartphone.interf.SmartphoneShopPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartphoneShopPriceServiceImpl implements SmartphoneShopPriceService {
    @Autowired
    private SmartphoneShopPriceRepository smartphoneShopPriceRepository;

    @Override
    public SmartphoneShopPriceModel save(SmartphoneShopPriceModel smartphoneShopPriceModel) {
        return smartphoneShopPriceRepository.save(smartphoneShopPriceModel);
    }
}
