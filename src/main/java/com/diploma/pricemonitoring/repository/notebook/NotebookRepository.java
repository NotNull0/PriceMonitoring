package com.diploma.pricemonitoring.repository.notebook;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotebookRepository extends JpaRepository<NotebookModel, Long> {
    Optional<NotebookModel> findByName(String name);

    @Query(value = "select * from notebook_model",
            countQuery = "select count(*) from notebook_model",
            nativeQuery = true)
    Page<NotebookModel> findAllPageable(Pageable pageable);

}
