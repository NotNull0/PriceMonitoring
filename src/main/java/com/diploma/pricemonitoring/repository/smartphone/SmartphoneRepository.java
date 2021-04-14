package com.diploma.pricemonitoring.repository.smartphone;

import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartphoneModel, Long> {
    Optional<SmartphoneModel> findByName(String name);

    @Query(value = "select * from smartphone_model",
            countQuery = "select count(*) from smartphone_model",
            nativeQuery = true)
    Page<SmartphoneModel> findAllPageable(Pageable pageable);
}
