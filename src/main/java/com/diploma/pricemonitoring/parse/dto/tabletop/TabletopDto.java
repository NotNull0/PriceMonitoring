package com.diploma.pricemonitoring.parse.dto.tabletop;

import com.diploma.pricemonitoring.modifications.NotebookConfiguration;
import com.diploma.pricemonitoring.modifications.TabletopConfiguration;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;
import org.jsoup.nodes.Document;

import java.util.List;

import static com.diploma.pricemonitoring.modifications.TabletopConfiguration.getDescription;

public class TabletopDto {
    private final String idProduct;
    private String name;
    private String system;
    private String displayDiagonal;
    private String displayResolution;
    private String processor;
    private String storage;
    private String frontCamera;
    private String battery;
    private String material;
    private String weight;
    private String imageURL;
    private String description;
    private List<PriceDto> priceDto;

    public TabletopDto(Document document) {
        idProduct = NotebookConfiguration.getIdProduct(document);
        this.setImageURL(TabletopConfiguration.getImageURl(idProduct, document));
        this.setName(document.select(String.format("#item_bl_%s > div.cont-block-title.no-mobile > span", idProduct)).text());
        this.description = getDescription(document);
    }

    public String getDesc() {
        return description;
    }

    public TabletopDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public TabletopDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdProduct() {
        return idProduct;
    }

    @Override
    public String toString() {
        return "TabletopDto{" +
                "idProduct='" + idProduct + '\'' +
                ", name='" + name + '\'' +
                ", system='" + system + '\'' +
                ", displayDiagonal='" + displayDiagonal + '\'' +
                ", displayResolution='" + displayResolution + '\'' +
                ", processor='" + processor + '\'' +
                ", storage='" + storage + '\'' +
                ", frontCamera='" + frontCamera + '\'' +
                ", battery='" + battery + '\'' +
                ", material='" + material + '\'' +
                ", weight='" + weight + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", priceDto=" + priceDto +
                '}';
    }

    public void populate(Document document, TabletopDto tabletopDto) {
        TabletopConfiguration.populate(document, tabletopDto, this.idProduct);
    }

    public String getSystem() {
        return system;
    }

    public TabletopDto setSystem(String system) {
        this.system = system;
        return this;
    }

    public String getDisplayDiagonal() {
        return displayDiagonal;
    }

    public TabletopDto setDisplayDiagonal(String displayDiagonal) {
        this.displayDiagonal = displayDiagonal;
        return this;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public TabletopDto setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
        return this;
    }

    public String getProcessor() {
        return processor;
    }

    public TabletopDto setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public String getStorage() {
        return storage;
    }

    public TabletopDto setStorage(String storage) {
        this.storage = storage;
        return this;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public TabletopDto setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
        return this;
    }

    public String getBattery() {
        return battery;
    }

    public TabletopDto setBattery(String battery) {
        this.battery = battery;
        return this;
    }

    public String getMaterial() {
        return material;
    }

    public TabletopDto setMaterial(String material) {
        this.material = material;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public TabletopDto setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public TabletopDto setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public List<PriceDto> getPriceDto() {
        return priceDto;
    }

    public TabletopDto setPriceDto(List<PriceDto> priceDto) {
        this.priceDto = priceDto;
        return this;
    }
}
