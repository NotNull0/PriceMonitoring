package com.diploma.pricemonitoring.repository;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotebookRepository extends JpaRepository<NotebookModel,Long> {
    Optional<NotebookModel> findByName(String name);
}
