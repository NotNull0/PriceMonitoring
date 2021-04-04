package com.diploma.pricemonitoring.service.interf;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.parse.dto.notebooks.NotebookDto;

import java.util.Optional;

public interface NotebookService {
    Optional<NotebookModel> findByName(String name);
    NotebookModel save(NotebookDto notebookDto);
}
