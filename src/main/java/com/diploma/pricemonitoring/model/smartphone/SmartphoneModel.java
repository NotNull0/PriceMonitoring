package com.diploma.pricemonitoring.model.smartphone;

import com.diploma.pricemonitoring.model.User;
import com.diploma.pricemonitoring.parse.dto.smartphones.SmartphoneDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class SmartphoneModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    private String name;
    @Column
    private String display;
    @Column
    private String memory;
    @Column
    private String processor;
    @Column
    private String camera;
    @Column
    private String video;
    @Column
    private String battery;
    @Column
    private String weight;
    @Column(length = 2048)
    private String description;
    @Column
    private String imageURL;
    @OneToMany(mappedBy = "smartphoneModel")
    private List<SmartphonePriceModel> priceDto;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "smartphoneModels")
    private List<User> users;

    public SmartphoneModel(SmartphoneDto smartphoneDto) {
        this.setName(isEmptySet(smartphoneDto.getName()));
        this.setDisplay(isEmptySet(smartphoneDto.getDisplay()));
        this.setMemory(isEmptySet(smartphoneDto.getMemory()));
        this.setProcessor(isEmptySet(smartphoneDto.getProcessor()));
        this.setCamera(isEmptySet(smartphoneDto.getCamera()));
        this.setVideo(isEmptySet(smartphoneDto.getVideo()));
        this.setBattery(isEmptySet(smartphoneDto.getBattery()));
        this.setWeight(isEmptySet(smartphoneDto.getWeight()));
        this.setDescription(isEmptySet(smartphoneDto.getDescription()));
        this.setImageURL(isEmptySet(smartphoneDto.getImageURL()));
    }

    public SmartphoneModel() {

    }

    private String isEmptySet(String value) {
        if (value == null ||value.isEmpty()) {
            return "Інформація відсутня";
        }
        return value;
    }

    public Long getId() {
        return id;
    }

    public SmartphoneModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SmartphoneModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public SmartphoneModel setDisplay(String display) {
        this.display = display;
        return this;
    }

    public String getMemory() {
        return memory;
    }

    public SmartphoneModel setMemory(String memory) {
        this.memory = memory;
        return this;
    }

    public String getProcessor() {
        return processor;
    }

    public SmartphoneModel setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public String getCamera() {
        return camera;
    }

    public SmartphoneModel setCamera(String camera) {
        this.camera = camera;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public SmartphoneModel setVideo(String video) {
        this.video = video;
        return this;
    }

    public String getBattery() {
        return battery;
    }

    public SmartphoneModel setBattery(String battery) {
        this.battery = battery;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public SmartphoneModel setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SmartphoneModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public SmartphoneModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public List<SmartphonePriceModel> getPriceDto() {
        return priceDto;
    }

    public SmartphoneModel setPriceDto(List<SmartphonePriceModel> priceDto) {
        this.priceDto = priceDto;
        return this;
    }
}
