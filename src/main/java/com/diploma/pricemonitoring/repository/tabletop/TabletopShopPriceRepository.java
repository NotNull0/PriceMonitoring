package com.diploma.pricemonitoring.repository.tabletop;

import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneShopPriceModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetShopPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabletopShopPriceRepository extends JpaRepository<PlanshetShopPriceModel, Long> {

    List<SmartphoneShopPriceModel> findAllByModelPrice(SmartphonePriceModel modelPrice);

    @Query(nativeQuery = true,
            value = "select * from planshet_shop_price_model p " +
                    "join planshet_price_model ppm on ppm.id = p.model_price_id " +
                    "join planshet_model pm on pm.id = ppm.planshet_model_id " +
                    "where p.price =?1 and pm.id=?2 and ppm.shop=?3")
    List<PlanshetShopPriceModel> getCountWritesFromShop(Integer price, Long tabletopId, String shop);
}
