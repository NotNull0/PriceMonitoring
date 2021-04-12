package com.diploma.pricemonitoring.repository.tabletop;

import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TabletopRepository extends JpaRepository<PlanshetModel, Long> {
    Optional<PlanshetModel> findByName(String name);
}
