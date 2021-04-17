package com.diploma.pricemonitoring.service.tabletop.interf;

import com.diploma.pricemonitoring.dto.tabletop.TabletopShopDto;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;

import java.util.List;

public interface TabletopPriceService {
    PlanshetPriceModel save(PlanshetPriceModel model);
    List<PlanshetPriceModel> getShopsByTabletopId(Long id);
    List<TabletopShopDto> getShopsByTabletopIdDto(Long id);

}
