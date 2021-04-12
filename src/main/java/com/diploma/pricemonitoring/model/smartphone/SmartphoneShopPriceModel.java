package com.diploma.pricemonitoring.model.smartphone;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class SmartphoneShopPriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private Integer price;

    private Timestamp datePrice;

    @ManyToOne
    private SmartphonePriceModel modelPrice;

    public Long getId() {
        return id;
    }

    public SmartphoneShopPriceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public SmartphoneShopPriceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Timestamp getDatePrice() {
        return datePrice;
    }

    public SmartphoneShopPriceModel setDatePrice(Timestamp datePrice) {
        this.datePrice = datePrice;
        return this;
    }

    public SmartphonePriceModel getModelPrice() {
        return modelPrice;
    }

    public SmartphoneShopPriceModel setModelPrice(SmartphonePriceModel modelPrice) {
        this.modelPrice = modelPrice;
        return this;
    }
}
