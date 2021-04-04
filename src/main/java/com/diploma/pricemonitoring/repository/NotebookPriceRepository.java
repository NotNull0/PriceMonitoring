package com.diploma.pricemonitoring.repository;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.model.notebook.NotebookPriceModel;
import com.diploma.pricemonitoring.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotebookPriceRepository extends JpaRepository<NotebookPriceModel, Long> {
    List<NotebookPriceModel> findAllByNotebookModelId(Long notebookModel_id);
    Optional<NotebookPriceModel> findByShopAndNotebookModel(Shop shop, NotebookModel notebookModel);
}
