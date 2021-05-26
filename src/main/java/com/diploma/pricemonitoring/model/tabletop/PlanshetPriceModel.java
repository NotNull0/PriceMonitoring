package com.diploma.pricemonitoring.model.tabletop;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class PlanshetPriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private Integer price;

    @Column(length = 2048)
    private String sellerLink;

    @Column
    @Enumerated(EnumType.STRING)
    private Shop shop;

    @ManyToOne
    private PlanshetModel planshetModel;

    @OneToMany(mappedBy = "modelPrice")
    private List<PlanshetShopPriceModel> prices;

    public PlanshetPriceModel(PriceDto notebookPriceDto) {
        this.setShop(notebookPriceDto.getShop());
        this.setPrice(notebookPriceDto.getPrice());
        this.setSellerLink(notebookPriceDto.getSellerLink());
    }

    public String getSellerLink() {
        return sellerLink;
    }

    public PlanshetPriceModel setSellerLink(String sellerLink) {
        this.sellerLink = sellerLink;
        return this;
    }

    public PlanshetPriceModel() {

    }

    public Long getId() {
        return id;
    }

    public PlanshetPriceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public PlanshetPriceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public PlanshetPriceModel setShop(Shop shop) {
        this.shop = shop;
        return this;
    }

    public PlanshetModel getPlanshetModel() {
        return planshetModel;
    }

    public PlanshetPriceModel setPlanshetModel(PlanshetModel model) {
        this.planshetModel = model;
        return this;
    }

    public List<PlanshetShopPriceModel> getPrices() {
        return prices;
    }

    public PlanshetPriceModel setPrices(List<PlanshetShopPriceModel> prices) {
        this.prices = prices;
        return this;
    }
}
