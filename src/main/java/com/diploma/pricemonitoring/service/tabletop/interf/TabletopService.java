package com.diploma.pricemonitoring.service.tabletop.interf;

import com.diploma.pricemonitoring.dto.tabletop.TabletopBaseDto;
import com.diploma.pricemonitoring.dto.tabletop.TabletopBaseShopDto;
import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import com.diploma.pricemonitoring.parse.dto.tabletop.TabletopDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TabletopService {
    Optional<PlanshetModel> findByName(String name);

    PlanshetModel save(TabletopDto tabletopDto);

    Page<PlanshetModel> findAllPageable(Pageable pageable);

    Page<TabletopBaseDto> findAllPageableDto(Pageable pageable);

    TabletopBaseShopDto findOne(Long id);

    List<TabletopBaseShopDto> getTabletopBaseShopDtos();

}
