package com.diploma.pricemonitoring.repository.tabletop;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TabletopPriceRepository extends JpaRepository<PlanshetPriceModel, Long> {
    Optional<PlanshetPriceModel> findByShopAndPlanshetModel(Shop shop, PlanshetModel tableModel);

    @Query(nativeQuery = true,
            value = "select * from planshet_price_model pl " +
                    "join planshet_model pm on pl.planshet_model_id = pm.id " +
                    "where pm.id=? and pl.shop!='NOT_FOUND'")
    List<PlanshetPriceModel> getShopsByTabletopId(Long id);
}
