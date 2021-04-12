package com.diploma.pricemonitoring.service.tabletop;

import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetShopPriceModel;
import com.diploma.pricemonitoring.repository.tabletop.TabletopPriceRepository;
import com.diploma.pricemonitoring.repository.tabletop.TabletopShopPriceRepository;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TabletopPriceServiceImpl implements TabletopPriceService {

    @Autowired
    private TabletopPriceRepository tabletopPriceRepository;
    @Autowired
    private TabletopShopPriceRepository tabletopShopPriceRepository;

    public static void main(String[] args) {
        if (1 < 1) {
            System.out.println("OK");
        } else {
            System.out.println("NOOK");
        }
    }

    @Override
    public PlanshetPriceModel save(PlanshetPriceModel model) {
        Optional<PlanshetPriceModel> presentShop = tabletopPriceRepository.findByShopAndPlanshetModel(model.getShop(), model.getPlanshetModel());
        PlanshetPriceModel tabletopModel;
        if (presentShop.isEmpty()) {
            tabletopModel = tabletopPriceRepository.save(model);
            saveShopPrice(model, tabletopModel);
        } else {
            tabletopModel = presentShop.get();
            boolean isNotNeedUpdate = IsPriceWasUpdated(model, presentShop);
            if (!isNotNeedUpdate) {
                int countWritesFromShop = tabletopShopPriceRepository.getCountWritesFromShop(model.getPrice(), tabletopModel.getPlanshetModel().getId(), tabletopModel.getShop().name()).size();
                System.out.println("SIZE + " + countWritesFromShop);
                System.out.println(
                        "MODEL PRICE --> " + model.getPrice() + "\n" +
                                "TABLETOP ID --> " + tabletopModel.getPlanshetModel().getId() + "\n" +
                                "SHOP --> " + tabletopModel.getShop().getValue() + "\n"
                );
                if (countWritesFromShop < 1) {
                    saveShopPrice(model, tabletopModel);
                }
            }
            tabletopPriceRepository.save(tabletopModel.setPrice(model.getPrice()));
        }
        return tabletopModel;
    }

    private boolean IsPriceWasUpdated(PlanshetPriceModel model, Optional<PlanshetPriceModel> presentShop) {
        return presentShop.get().getPrice().equals(model.getPrice());
    }

    private void saveShopPrice(PlanshetPriceModel model, PlanshetPriceModel smartphonePriceModel) {
        PlanshetShopPriceModel smartphoneShopPriceModel =
                new PlanshetShopPriceModel()
                        .setDatePrice(Timestamp.valueOf(LocalDateTime.now()))
                        .setPrice(model.getPrice())
                        .setModelPrice(smartphonePriceModel);
        tabletopShopPriceRepository.save(smartphoneShopPriceModel);
    }

}
