package com.diploma.pricemonitoring.repository.smartphone;

import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneShopPriceModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetShopPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneShopPriceRepository extends JpaRepository<SmartphoneShopPriceModel, Long> {
    List<SmartphoneShopPriceModel> findAllByModelPrice(SmartphonePriceModel modelPrice);

    @Query(nativeQuery = true,
            value = "select * from smartphone_shop_price_model p " +
                    "join smartphone_price_model ppm on ppm.id = p.model_price_id " +
                    "join smartphone_model pm on pm.id = ppm.smartphone_model_id " +
                    "where p.price =?1 and pm.id=?2 and ppm.shop=?3")
    List<SmartphoneShopPriceModel> getCountWritesFromShop(Integer price, Long tabletopId, String shop);
}
