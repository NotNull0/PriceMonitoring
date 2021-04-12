package com.diploma.pricemonitoring.model.smartphone;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class SmartphonePriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private Integer price;

    @Column
    @Enumerated(EnumType.STRING)
    private Shop shop;

    @ManyToOne
    private SmartphoneModel smartphoneModel;

    @OneToMany(mappedBy = "modelPrice")
    private List<SmartphoneShopPriceModel> prices;

    public SmartphonePriceModel(PriceDto notebookPriceDto) {
        this.setShop(notebookPriceDto.getShop());
        this.setPrice(notebookPriceDto.getPrice());
    }

    public SmartphonePriceModel() {

    }

    public Long getId() {
        return id;
    }

    public SmartphonePriceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public SmartphonePriceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public SmartphonePriceModel setShop(Shop shop) {
        this.shop = shop;
        return this;
    }

    public SmartphoneModel getSmartphoneModel() {
        return smartphoneModel;
    }

    public SmartphonePriceModel setSmartphoneModel(SmartphoneModel smartphoneModel) {
        this.smartphoneModel = smartphoneModel;
        return this;
    }

    public List<SmartphoneShopPriceModel> getPrices() {
        return prices;
    }

    public SmartphonePriceModel setPrices(List<SmartphoneShopPriceModel> prices) {
        this.prices = prices;
        return this;
    }
}
