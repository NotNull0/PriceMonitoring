package com.diploma.pricemonitoring.model.notebook;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class NotebookShopPriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private Integer price;

    private Timestamp datePrice;

    @ManyToOne
    private NotebookPriceModel modelPrice;

    public NotebookShopPriceModel() {
        this.datePrice = Timestamp.valueOf(LocalDateTime.now());
    }

    public Timestamp getDatePrice() {
        return datePrice;
    }

    public NotebookShopPriceModel setDatePrice(Timestamp datePrice) {
        this.datePrice = datePrice;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NotebookShopPriceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public NotebookShopPriceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public NotebookPriceModel getModelPrice() {
        return modelPrice;
    }

    public NotebookShopPriceModel setModelPrice(NotebookPriceModel modelPrice) {
        this.modelPrice = modelPrice;
        return this;
    }
}
