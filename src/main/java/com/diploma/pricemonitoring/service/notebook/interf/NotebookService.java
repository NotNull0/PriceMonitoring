package com.diploma.pricemonitoring.service.notebook.interf;

import com.diploma.pricemonitoring.dto.NotebookBaseDto;
import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.parse.dto.notebooks.NotebookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NotebookService {
    Optional<NotebookModel> findByName(String name);

    NotebookModel save(NotebookDto notebookDto);

    Page<NotebookModel> findAllPageable(Pageable pageable);

    Page<NotebookBaseDto> findAllPageableBaseModel(Pageable pageable);
}
