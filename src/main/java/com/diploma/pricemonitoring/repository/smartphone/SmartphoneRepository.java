package com.diploma.pricemonitoring.repository.smartphone;

import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartphoneModel,Long> {
    Optional<SmartphoneModel> findByName(String name);
}
