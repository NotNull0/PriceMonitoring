package com.diploma.pricemonitoring.repository.tabletop;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TabletopPriceRepository extends JpaRepository<PlanshetPriceModel, Long> {
    Optional<PlanshetPriceModel> findByShopAndPlanshetModel(Shop shop, PlanshetModel tableModel);
}
