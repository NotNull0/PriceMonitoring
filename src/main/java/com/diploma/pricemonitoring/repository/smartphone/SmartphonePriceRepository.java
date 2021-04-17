package com.diploma.pricemonitoring.repository.smartphone;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SmartphonePriceRepository extends JpaRepository<SmartphonePriceModel, Long> {
    List<SmartphonePriceModel> findAllBySmartphoneModelId(Long aLong);

    Optional<SmartphonePriceModel> findByShopAndSmartphoneModel(Shop shop, SmartphoneModel smartphoneModel);

    @Query(nativeQuery = true,
            value = "select * from smartphone_price_model pl " +
                    "join smartphone_model pm on pl.smartphone_model_id = pm.id " +
                    "where pm.id=? and pl.shop!='NOT_FOUND'")
    List<SmartphonePriceModel> getShopsBySmartphoneId(Long id);
}
