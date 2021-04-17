package com.diploma.pricemonitoring.dto.smartphone;

import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import lombok.Data;


@Data
public class SmartphoneBaseDto {
    private Long id;
    private String name;
    private String display;
    private String memory;
    private String processor;
    private String camera;
    private String video;
    private String battery;
    private String weight;
    private String description;
    private String imageURL;

    public SmartphoneBaseDto(SmartphoneModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.display = model.getDisplay();
        this.memory = model.getMemory();
        this.processor = model.getProcessor();
        this.camera = model.getCamera();
        this.video = model.getVideo();
        this.battery = model.getBattery();
        this.weight = model.getWeight();
        this.description = model.getDescription();
        this.imageURL = model.getImageURL();
    }
}
