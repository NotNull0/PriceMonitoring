package com.diploma.pricemonitoring.service.notebook;

import com.diploma.pricemonitoring.model.notebook.NotebookPriceModel;
import com.diploma.pricemonitoring.model.notebook.NotebookShopPriceModel;
import com.diploma.pricemonitoring.repository.notebook.NotebookPriceRepository;
import com.diploma.pricemonitoring.repository.notebook.NotebookShopPriceRepository;
import com.diploma.pricemonitoring.service.notebook.interf.NotebookPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotebookPriceServiceImpl implements NotebookPriceService {

    @Autowired
    private NotebookPriceRepository notebookPriceRepository;
    @Autowired
    private NotebookShopPriceRepository notebookShopPriceRepository;


    @Override
    public NotebookPriceModel save(NotebookPriceModel model) {
        Optional<NotebookPriceModel> presentShop = notebookPriceRepository.findByShopAndNotebookModel(model.getShop(), model.getNotebookModel());
        NotebookPriceModel notebookPriceModel;
        if (presentShop.isEmpty()) {
            notebookPriceModel = notebookPriceRepository.save(model);
            saveShopPrice(model, notebookPriceModel);
        } else {
            notebookPriceModel = presentShop.get();
            boolean isNotNeedUpdate = IsPriceWasUpdated(model, presentShop);
            if (!isNotNeedUpdate) {
                int countWritesFromShop = notebookShopPriceRepository.getCountWritesFromShop(model.getPrice(), notebookPriceModel.getNotebookModel().getId(), notebookPriceModel.getShop().name()).size();
                System.out.println("SIZE + " + countWritesFromShop);
                System.out.println(
                        "MODEL PRICE --> " + model.getPrice() + "\n" +
                                "TABLETOP ID --> " + notebookPriceModel.getNotebookModel().getId() + "\n" +
                                "SHOP --> " + notebookPriceModel.getShop().name() + "\n"
                );
                if (countWritesFromShop < 1) {
                    saveShopPrice(model, notebookPriceModel);
                }
            }
            notebookPriceRepository.save(notebookPriceModel.setPrice(model.getPrice()));
        }
        return notebookPriceModel;
    }

    private boolean IsPriceWasUpdated(NotebookPriceModel model, Optional<NotebookPriceModel> presentShop) {
        return presentShop.get().getPrice().equals(model.getPrice());
    }

    private void saveShopPrice(NotebookPriceModel model, NotebookPriceModel notebookPriceModel) {
        NotebookShopPriceModel notebookShopPriceModel = new NotebookShopPriceModel().setPrice(model.getPrice()).setModelPrice(notebookPriceModel);
        notebookShopPriceRepository.save(notebookShopPriceModel);
    }

}
