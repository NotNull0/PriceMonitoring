package com.diploma.pricemonitoring.dto;

import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import lombok.Data;

@Data
public class TabletopBaseDto {
    private String name;
    private String os;
    private String displayDiagonal;
    private String displayResolution;
    private String processor;
    private String storage;
    private String frontCamera;
    private String battery;
    private String material;
    private String weight;
    private String imageURL;

    public TabletopBaseDto(PlanshetModel planshetModel) {
        this.name = planshetModel.getName();
        this.os = planshetModel.getOs();
        this.displayDiagonal = planshetModel.getDisplayDiagonal();
        this.displayResolution = planshetModel.getDisplayResolution();
        this.processor = planshetModel.getProcessor();
        this.storage = planshetModel.getStorage();
        this.frontCamera = planshetModel.getFrontCamera();
        this.battery = planshetModel.getBattery();
        this.material = planshetModel.getMaterial();
        this.weight = planshetModel.getWeight();
        this.imageURL = planshetModel.getImageURL();
    }
}