package com.diploma.pricemonitoring.model.notebook;

import com.diploma.pricemonitoring.parse.dto.notebooks.NotebookDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class NotebookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    private String name;
    @Column
    private String display;
    @Column
    private String processor;
    @Column
    private String OZU;
    @Column
    private String Storage;
    @Column
    private String Weight;
    @Column(length = 2048)
    private String description;
    @Column
    private String imageURL;

    @OneToMany(mappedBy = "notebookModel")
    private List<NotebookPriceModel> notebookPriceModels;

    public NotebookModel(NotebookDto notebookDto) {
        this.setDisplay(isEmptySet(notebookDto.getDisplay()));
        this.setName(isEmptySet(notebookDto.getName()));
        this.setOZU(isEmptySet(notebookDto.getOZU()));
        this.setProcessor(isEmptySet(notebookDto.getProcessor()));
        this.setStorage(isEmptySet(notebookDto.getStorage()));
        this.setWeight(isEmptySet(notebookDto.getWeight()));
        this.setDescription(isEmptySet(notebookDto.getDescription()));
        this.setImageURL(isEmptySet(notebookDto.getImageURL()));
    }

    private String isEmptySet(String value) {
        if (value == null ||value.isEmpty()) {
            return "Інформація відсутня";
        }
        return value;
    }

    public String getImageURL() {
        return imageURL;
    }

    public NotebookModel setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public NotebookModel() {
    }

    public String getDescription() {
        return description;
    }

    public NotebookModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NotebookModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public NotebookModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public NotebookModel setDisplay(String display) {
        this.display = display;
        return this;
    }

    public String getProcessor() {
        return processor;
    }

    public NotebookModel setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public String getOZU() {
        return OZU;
    }

    public NotebookModel setOZU(String OZU) {
        this.OZU = OZU;
        return this;
    }

    public String getStorage() {
        return Storage;
    }

    public NotebookModel setStorage(String storage) {
        Storage = storage;
        return this;
    }

    public String getWeight() {
        return Weight;
    }

    public NotebookModel setWeight(String weight) {
        Weight = weight;
        return this;
    }

    public List<NotebookPriceModel> getNotebookPriceModels() {
        return notebookPriceModels;
    }

    public NotebookModel setNotebookPriceModels(List<NotebookPriceModel> notebookPriceModels) {
        this.notebookPriceModels = notebookPriceModels;
        return this;
    }
}
