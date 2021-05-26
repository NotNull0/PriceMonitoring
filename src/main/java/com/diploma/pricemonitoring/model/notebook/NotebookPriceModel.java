package com.diploma.pricemonitoring.model.notebook;

import com.diploma.pricemonitoring.model.Shop;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class NotebookPriceModel {
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
    private NotebookModel notebookModel;

    @OneToMany(mappedBy = "modelPrice")
    private List<NotebookShopPriceModel> prices;

    public NotebookPriceModel() {
    }

    public NotebookPriceModel(PriceDto notebookPriceDto) {
        this.setShop(notebookPriceDto.getShop());
        this.setPrice(notebookPriceDto.getPrice());
        this.setSellerLink(notebookPriceDto.getSellerLink());
    }

    public String getSellerLink() {
        return sellerLink;
    }

    public NotebookPriceModel setSellerLink(String sellerLink) {
        this.sellerLink = sellerLink;
        return this;
    }

    public List<NotebookShopPriceModel> getPrices() {
        return prices;
    }

    public NotebookPriceModel setPrices(List<NotebookShopPriceModel> prices) {
        this.prices = prices;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NotebookPriceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public NotebookPriceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Shop getShop() {
        return shop;
    }

    public NotebookPriceModel setShop(Shop shop) {
        this.shop = shop;
        return this;
    }

    public NotebookModel getNotebookModel() {
        return notebookModel;
    }

    public NotebookPriceModel setNotebookModel(NotebookModel notebookModel) {
        this.notebookModel = notebookModel;
        return this;
    }

    @Override
    public String toString() {
        return "NotebookPriceModel{" +
                "id=" + id +
                ", price=" + price +
                ", shop=" + shop +
                ", notebookModel=" + notebookModel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotebookPriceModel that = (NotebookPriceModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                shop == that.shop &&
                Objects.equals(notebookModel, that.notebookModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, shop, notebookModel);
    }
}
