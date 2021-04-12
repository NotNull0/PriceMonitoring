package com.diploma.pricemonitoring.repository.smartphone;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SmartphonePriceRepository extends JpaRepository<SmartphonePriceModel, Long> {
    List<SmartphonePriceModel> findAllBySmartphoneModelId(Long aLong);
    Optional<SmartphonePriceModel> findByShopAndSmartphoneModel(Shop shop, SmartphoneModel smartphoneModel);
}
