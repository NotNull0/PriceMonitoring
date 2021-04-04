package com.diploma.pricemonitoring.repository;

import com.diploma.pricemonitoring.model.notebook.NotebookPriceModel;
import com.diploma.pricemonitoring.model.notebook.NotebookShopPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookShopPriceRepository extends JpaRepository<NotebookShopPriceModel, Long> {
    List<NotebookShopPriceModel> findAllByModelPrice(NotebookPriceModel modelPrice);
}
