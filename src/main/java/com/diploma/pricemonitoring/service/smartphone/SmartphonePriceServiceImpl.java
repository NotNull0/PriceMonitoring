package com.diploma.pricemonitoring.service.smartphone;

import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneShopPriceModel;
import com.diploma.pricemonitoring.repository.smartphone.SmartphonePriceRepository;
import com.diploma.pricemonitoring.repository.smartphone.SmartphoneShopPriceRepository;
import com.diploma.pricemonitoring.service.smartphone.interf.SmartphonePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SmartphonePriceServiceImpl implements SmartphonePriceService {

    @Autowired
    private SmartphonePriceRepository smartphonePriceRepository;
    @Autowired
    private SmartphoneShopPriceRepository smartphoneShopPriceRepository;


    @Override
    public SmartphonePriceModel save(SmartphonePriceModel model) {
        Optional<SmartphonePriceModel> presentShop = smartphonePriceRepository.findByShopAndSmartphoneModel(model.getShop(), model.getSmartphoneModel());
        SmartphonePriceModel smartphonePriceModel;
        if (presentShop.isEmpty()) {
            smartphonePriceModel = smartphonePriceRepository.save(model);
            saveShopPrice(model, smartphonePriceModel);
        } else {
            smartphonePriceModel = presentShop.get();
            boolean isNotNeedUpdate = IsPriceWasUpdated(model, presentShop);
            if (!isNotNeedUpdate) {
                int countWritesFromShop = smartphoneShopPriceRepository.getCountWritesFromShop(model.getPrice(), smartphonePriceModel.getSmartphoneModel().getId(), smartphonePriceModel.getShop().name()).size();
                System.out.println("SIZE + " + countWritesFromShop);
                System.out.println(
                        "MODEL PRICE --> " + model.getPrice() + "\n" +
                                "TABLETOP ID --> " + smartphonePriceModel.getSmartphoneModel().getId() + "\n" +
                                "SHOP --> " + smartphonePriceModel.getShop().name() + "\n"
                );
                if (countWritesFromShop < 1) {
                    saveShopPrice(model, smartphonePriceModel);
                }
            }
            smartphonePriceRepository.save(smartphonePriceModel.setPrice(model.getPrice()));
        }
        return smartphonePriceModel;
    }

    private boolean IsPriceWasUpdated(SmartphonePriceModel model, Optional<SmartphonePriceModel> presentShop) {
        return presentShop.get().getPrice().equals(model.getPrice());
    }

    private void saveShopPrice(SmartphonePriceModel model, SmartphonePriceModel smartphonePriceModel) {
        SmartphoneShopPriceModel smartphoneShopPriceModel = new SmartphoneShopPriceModel()
                .setDatePrice(Timestamp.valueOf(LocalDateTime.now()))
                .setPrice(model.getPrice())
                .setModelPrice(smartphonePriceModel);
        smartphoneShopPriceRepository.save(smartphoneShopPriceModel);
    }

}
