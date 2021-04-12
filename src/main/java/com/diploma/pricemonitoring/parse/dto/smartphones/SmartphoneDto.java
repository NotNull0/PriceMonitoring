package com.diploma.pricemonitoring.parse.dto.smartphones;

import com.diploma.pricemonitoring.modifications.SmartphoneConfiguration;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;
import org.jsoup.nodes.Document;

import java.util.List;

import static com.diploma.pricemonitoring.modifications.NotebookConfiguration.getIdProduct;

public class SmartphoneDto {
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
    private List<PriceDto> priceDto;

    public SmartphoneDto(Document document) {
        String idProduct = getIdProduct(document);
        setName(SmartphoneConfiguration.getName(idProduct, document));
        setDescription(SmartphoneConfiguration.getDescription(document));
        setImageURL(SmartphoneConfiguration.getImageURl(idProduct, document));
    }

    public SmartphoneDto(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public SmartphoneDto setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    @Override
    public String toString() {
        return "SmartphoneDto{" + "\n" +
                "name='" + name + '\'' + "\n" +
                ", display='" + display + '\'' + "\n" +
                ", memory='" + memory + '\'' + "\n" +
                ", processor='" + processor + '\'' + "\n" +
                ", camera='" + camera + '\'' + "\n" +
                ", video='" + video + '\'' + "\n" +
                ", battery='" + battery + '\'' + "\n" +
                ", weight='" + weight + '\'' + "\n" +
                ", description='" + description + '\'' + "\n" +
                ", img='" + imageURL + '\'' + "\n" +
                ", priceDto=" + priceDto + "\n" +
                '}' + "\n";
    }

    public void marketToString(){
        System.out.println("DEVICE NAME --->" + name +"\n");
        System.out.println(this.priceDto.toString());
    }

    private String isNull(String value) {
        if (value != null) {
            return value;
        } else return "Інформація відсутня";
    }

    public String getDescription() {
        return isNull(description);
    }

    public SmartphoneDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return isNull(name);
    }

    public SmartphoneDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDisplay() {
        return isNull(display);
    }

    public SmartphoneDto setDisplay(String display) {
        this.display = display;
        return this;
    }

    public String getMemory() {
        return isNull(memory);
    }

    public SmartphoneDto setMemory(String memory) {
        this.memory = memory;
        return this;
    }

    public String getProcessor() {
        return isNull(processor);
    }

    public SmartphoneDto setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public String getCamera() {
        return isNull(camera);
    }

    public SmartphoneDto setCamera(String camera) {
        this.camera = camera;
        return this;
    }

    public String getVideo() {
        return isNull(video);
    }

    public SmartphoneDto setVideo(String video) {
        this.video = video;
        return this;
    }

    public String getBattery() {
        return isNull(battery);
    }

    public SmartphoneDto setBattery(String battery) {
        this.battery = battery;
        return this;
    }

    public String getWeight() {
        return isNull(weight);
    }

    public SmartphoneDto setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public List<PriceDto> getPriceDto() {
        return priceDto;
    }

    public SmartphoneDto setPriceDto(List<PriceDto> priceDto) {
        this.priceDto = priceDto;
        return this;
    }
}
