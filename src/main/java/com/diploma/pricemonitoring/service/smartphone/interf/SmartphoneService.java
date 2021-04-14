package com.diploma.pricemonitoring.service.smartphone.interf;

import com.diploma.pricemonitoring.dto.SmartphoneBaseDto;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.parse.dto.smartphones.SmartphoneDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface SmartphoneService {
    Optional<SmartphoneModel> findByName(String name);
    SmartphoneModel save(SmartphoneDto smartphoneDto);
    Page<SmartphoneModel> findAllPageable(Pageable pageable);
    Page<SmartphoneBaseDto> findAllPageableBaseModel(Pageable pageable);

}