package com.diploma.pricemonitoring.repository.tabletop;

import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TabletopRepository extends JpaRepository<PlanshetModel, Long> {
    Optional<PlanshetModel> findByName(String name);

    @Query(value = "select * from planshet_model",
            countQuery = "select count(*) from planshet_model",
            nativeQuery = true)
    Page<PlanshetModel> findAllPageable(Pageable pageable);
}
