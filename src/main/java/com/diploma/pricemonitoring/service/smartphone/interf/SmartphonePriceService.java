package com.diploma.pricemonitoring.service.smartphone.interf;


import com.diploma.pricemonitoring.dto.smartphone.SmartphoneShopDto;
import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;

import java.util.List;

public interface SmartphonePriceService {
    SmartphonePriceModel save(SmartphonePriceModel model);

    List<SmartphonePriceModel> getShopsBySmartphoneId(Long id);

    List<SmartphoneShopDto> getShopsBySmartphoneIdDto(Long id);
}
