package com.diploma.pricemonitoring.model.tabletop;

import com.diploma.pricemonitoring.parse.dto.tabletop.TabletopDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class PlanshetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    private String name;
    @Column
    private String os;
    @Column
    private String displayDiagonal;
    @Column
    private String displayResolution;
    @Column
    private String processor;
    @Column
    private String storage;
    @Column
    private String frontCamera;
    @Column
    private String battery;
    @Column
    private String material;
    @Column
    private String weight;
    @Column(length = 2048)
    private String description;
    @Column
    private String imageURL;
    @OneToMany(mappedBy = "planshetModel")
    private List<PlanshetPriceModel> prices;

    public PlanshetModel(TabletopDto tabletopDto) {
        this.name = isEmptySet(tabletopDto.getName());
        this.os = isEmptySet(tabletopDto.getSystem());
        this.displayDiagonal = isEmptySet(tabletopDto.getDisplayDiagonal());
        this.displayResolution = isEmptySet(tabletopDto.getDisplayResolution());
        this.processor = isEmptySet(tabletopDto.getProcessor());
        this.storage = isEmptySet(tabletopDto.getStorage());
        this.frontCamera = isEmptySet(tabletopDto.getFrontCamera());
        this.battery = isEmptySet(tabletopDto.getBattery());
        this.material = isEmptySet(tabletopDto.getMaterial());
        this.weight = isEmptySet(tabletopDto.getWeight());
        this.imageURL = isEmptySet(tabletopDto.getImageURL());
        this.description = isEmptySet(tabletopDto.getDesc());
    }

    public PlanshetModel() {

    }

    private String isEmptySet(String value) {
        if (value == null || value.isEmpty()) {
            return "Інформація відсутня";
        }
        return value;
    }

    public Long getId() {
        return id;
    }

    public PlanshetModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlanshetModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getOs() {
        return os;
    }

    public PlanshetModel setOs(String system) {
        this.os = system;
        return this;
    }

    public String getDisplayDiagonal() {
        return displayDiagonal;
    }

    public PlanshetModel setDisplayDiagonal(String displayDiagonal) {
        this.displayDiagonal = displayDiagonal;
        return this;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public PlanshetModel setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
        return this;
    }

    public String getProcessor() {
        return processor;
    }

    public PlanshetModel setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public String getStorage() {
        return storage;
    }

    public PlanshetModel setStorage(String storage) {
        this.storage = storage;
        return this;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public PlanshetModel setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
        return this;
    }

    public String getBattery() {
        return battery;
    }

    public PlanshetModel setBattery(String battery) {
        this.battery = battery;
        return this;
    }

    public String getMaterial() {
        return material;
    }

    public PlanshetModel setMaterial(String material) {
        this.material = material;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public PlanshetModel setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlanshetModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public PlanshetModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public List<PlanshetPriceModel> getPrices() {
        return prices;
    }

    public PlanshetModel setPrices(List<PlanshetPriceModel> priceDto) {
        this.prices = priceDto;
        return this;
    }
}
