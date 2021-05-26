package com.diploma.pricemonitoring.parser;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;

import java.util.List;

public interface Data {
    List<NotebookModel> getNotebooks();

    List<SmartphoneModel> getSmartphones();

    List<PlanshetModel> getTableTops();
}
