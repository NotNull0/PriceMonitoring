package com.diploma.pricemonitoring.service.tabletop;

import com.diploma.pricemonitoring.model.tabletop.PlanshetShopPriceModel;
import com.diploma.pricemonitoring.repository.tabletop.TabletopShopPriceRepository;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopShopPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TabletopShopPriceServiceImpl implements TabletopShopPriceService {
    @Autowired
    private TabletopShopPriceRepository tabletopShopPriceRepository;

    @Override
    public PlanshetShopPriceModel save(PlanshetShopPriceModel planshetShopPriceModel) {
        return tabletopShopPriceRepository.save(planshetShopPriceModel);
    }
}
