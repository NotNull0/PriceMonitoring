package com.diploma.pricemonitoring.dto;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.parse.ProductType;
import lombok.Data;

import static com.diploma.pricemonitoring.utils.UtilsService.generateDescription;

@Data
public class NotebookBaseDto {
    private Long id;
    private String name;
    private String display;
    private String processor;
    private String OZU;
    private String storage;
    private String weight;
    private String description;
    private String imageURL;

    public NotebookBaseDto(NotebookModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.display = model.getDisplay();
        this.processor = model.getProcessor();
        this.OZU = model.getOZU();
        this.storage = model.getStorage();
        this.weight = model.getWeight();
        this.imageURL = model.getImageURL();
        this.description = generateDescription(ProductType.NOTEBOOK, model.getDescription());

    }
}
