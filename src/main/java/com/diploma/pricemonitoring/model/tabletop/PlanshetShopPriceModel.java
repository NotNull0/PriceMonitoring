package com.diploma.pricemonitoring.model.tabletop;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class PlanshetShopPriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private Integer price;

    private Timestamp datePrice;

    @ManyToOne
    private PlanshetPriceModel modelPrice;

    public Long getId() {
        return id;
    }

    public PlanshetShopPriceModel() {
    }

    public PlanshetShopPriceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public PlanshetShopPriceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Timestamp getDatePrice() {
        return datePrice;
    }

    public PlanshetShopPriceModel setDatePrice(Timestamp datePrice) {
        this.datePrice = datePrice;
        return this;
    }

    public PlanshetPriceModel getModelPrice() {
        return modelPrice;
    }

    public PlanshetShopPriceModel setModelPrice(PlanshetPriceModel modelPrice) {
        this.modelPrice = modelPrice;
        return this;
    }
}
