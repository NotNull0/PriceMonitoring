package com.diploma.pricemonitoring.parse.dto.notebooks;

import org.jsoup.nodes.Document;

import java.util.LinkedList;
import java.util.List;

import static com.diploma.pricemonitoring.modifications.NotebookConfiguration.*;
import static com.diploma.pricemonitoring.modifications.NotebookConfiguration.getNotebookWeight;

public class NotebookDto {
    private String name;
    private String display;
    private String processor;
    private String OZU;
    private String Storage;
    private String Weight;
    private List<PriceDto> priceDtos = new LinkedList<>();
    private String description;
    private String imageURL;

    public NotebookDto(Document document) {
        this.setName(getNotebookName(document));
        this.setDisplay(getNotebookDisplay(document));
        this.setProcessor(getNotebookProcessor(document));
        this.setOZU(getNotebookOZU(document));
        this.setStorage(getNotebookStorage(document));
        this.setWeight(getNotebookWeight(document));
    }

    public NotebookDto() {
    }

    public String getImageURL() {
        return imageURL;
    }

    public NotebookDto setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NotebookDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PriceDto> getPriceNotebooks() {
        return priceDtos;
    }

    public NotebookDto setPriceNotebooks(List<PriceDto> priceDtos) {
        this.priceDtos = priceDtos;
        return this;
    }

    public String getName() {
        return name;
    }

    public NotebookDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public NotebookDto setDisplay(String display) {
        this.display = display;
        return this;
    }

    public String getProcessor() {
        return processor;
    }

    public NotebookDto setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public String getOZU() {
        return OZU;
    }

    public NotebookDto setOZU(String OZU) {
        this.OZU = OZU;
        return this;
    }

    public String getStorage() {
        return Storage;
    }

    public NotebookDto setStorage(String storage) {
        Storage = storage;
        return this;
    }

    public String getWeight() {
        return Weight;
    }

    public NotebookDto setWeight(String weight) {
        Weight = weight;
        return this;
    }

    @Override
    public String toString() {
        return "NotebookDto{" +
                "name='" + name + '\'' + "\n"+
                "[display]='" + display + '\'' + "\n"+
                "[processor]='" + processor + '\'' + "\n"+
                "[OZU]='" + OZU + '\'' + "\n"+
                "[Storage]='" + Storage + '\'' + "\n"+
                "[Weight]='" + Weight + '\'' + "\n"+
                "[priceNotebooks]=" + priceDtos +
                "[img]=" + imageURL +
                '}' +"\n" + "=====================================";
    }
}
