package com.diploma.pricemonitoring.repository.notebook;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.model.notebook.NotebookPriceModel;
import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotebookPriceRepository extends JpaRepository<NotebookPriceModel, Long> {
    List<NotebookPriceModel> findAllByNotebookModelId(Long notebookModel_id);

    Optional<NotebookPriceModel> findByShopAndNotebookModel(Shop shop, NotebookModel notebookModel);

    @Query(nativeQuery = true,
            value = "select * from notebook_price_model pl " +
                    "join notebook_model pm on pl.notebook_model_id = pm.id " +
                    "where pm.id=? and pl.shop!='NOT_FOUND'")
    List<NotebookPriceModel> getShopsByNotebookId(Long id);
}
