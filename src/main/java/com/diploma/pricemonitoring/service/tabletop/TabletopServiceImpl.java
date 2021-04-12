package com.diploma.pricemonitoring.service.tabletop;

import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;
import com.diploma.pricemonitoring.parse.dto.tabletop.TabletopDto;
import com.diploma.pricemonitoring.repository.tabletop.TabletopRepository;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopPriceService;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TabletopServiceImpl implements TabletopService {
    @Autowired
    private TabletopRepository tabletopRepository;
    @Autowired
    private TabletopPriceService tabletopPriceService;

    @Override
    public Optional<PlanshetModel> findByName(String name) {
        return tabletopRepository.findByName(name);
    }

    @Override
    public PlanshetModel save(TabletopDto tabletopDto) {
        Optional<PlanshetModel> optional = findByName(tabletopDto.getName());
        PlanshetModel model = new PlanshetModel(tabletopDto);

        try {

            if (optional.isEmpty()) {
                PlanshetModel save = tabletopRepository.save(model);
                List<PlanshetPriceModel> planshetPriceModels = parseListNotebookPriceDtoToModel(tabletopDto.getPriceDto(), save);
                this.saveAllNotebookPrices(planshetPriceModels);
                return save;

            } else {
                PlanshetModel currentModel = optional.get();
                List<PlanshetPriceModel> planshetPriceModels = parseListNotebookPriceDtoToModel(tabletopDto.getPriceDto(), currentModel);
                this.saveAllNotebookPrices(planshetPriceModels);
                return tabletopRepository.save(currentModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    private void saveAllNotebookPrices(List<PlanshetPriceModel> list) {
        list.forEach(model -> {
            tabletopPriceService.save(model);
        });
    }

    private List<PlanshetPriceModel> parseListNotebookPriceDtoToModel(List<PriceDto> priceDtos, PlanshetModel planshetModel) {
        return priceDtos.stream().map(priceNotebook -> parseNotebookPriceDtoToModel(priceNotebook, planshetModel)).collect(Collectors.toList());
    }

    private PlanshetPriceModel parseNotebookPriceDtoToModel(PriceDto priceDto, PlanshetModel planshetModel) {
        return new PlanshetPriceModel(priceDto).setPlanshetModel(planshetModel);
    }
}
