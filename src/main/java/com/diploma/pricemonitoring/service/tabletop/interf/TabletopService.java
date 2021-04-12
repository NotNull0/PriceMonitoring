package com.diploma.pricemonitoring.service.tabletop.interf;

import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import com.diploma.pricemonitoring.parse.dto.tabletop.TabletopDto;

import java.util.Optional;

public interface TabletopService {
    Optional<PlanshetModel> findByName(String name);

    PlanshetModel save(TabletopDto tabletopDto);
}
