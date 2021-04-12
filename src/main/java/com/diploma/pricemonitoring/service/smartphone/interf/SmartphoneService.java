package com.diploma.pricemonitoring.service.smartphone.interf;

import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.parse.dto.smartphones.SmartphoneDto;

import java.util.Optional;


public interface SmartphoneService {
    Optional<SmartphoneModel> findByName(String name);
    SmartphoneModel save(SmartphoneDto smartphoneDto);
}
