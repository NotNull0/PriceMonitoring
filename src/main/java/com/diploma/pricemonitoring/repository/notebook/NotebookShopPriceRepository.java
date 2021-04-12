package com.diploma.pricemonitoring.repository.notebook;

import com.diploma.pricemonitoring.model.notebook.NotebookPriceModel;
import com.diploma.pricemonitoring.model.notebook.NotebookShopPriceModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneShopPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookShopPriceRepository extends JpaRepository<NotebookShopPriceModel, Long> {
    List<NotebookShopPriceModel> findAllByModelPrice(NotebookPriceModel modelPrice);

    @Query(nativeQuery = true,
            value = "select * from notebook_shop_price_model p " +
                    "join notebook_price_model ppm on ppm.id = p.model_price_id " +
                    "join notebook_model pm on pm.id = ppm.notebook_model_id " +
                    "where p.price =?1 and pm.id=?2 and ppm.shop=?3")
    List<NotebookShopPriceModel> getCountWritesFromShop(Integer price, Long tabletopId, String shop);

}
