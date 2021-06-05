package com.diploma.pricemonitoring.service.tabletop;

import com.diploma.pricemonitoring.dto.smartphone.SmartphoneBaseDto;
import com.diploma.pricemonitoring.dto.smartphone.SmartphoneBaseShopDto;
import com.diploma.pricemonitoring.dto.tabletop.TabletopBaseDto;
import com.diploma.pricemonitoring.dto.tabletop.TabletopBaseShopDto;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetModel;
import com.diploma.pricemonitoring.model.tabletop.PlanshetPriceModel;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;
import com.diploma.pricemonitoring.parse.dto.tabletop.TabletopDto;
import com.diploma.pricemonitoring.repository.tabletop.TabletopRepository;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopPriceService;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.table.TableModel;
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
    public List<TabletopBaseShopDto> getTabletopBaseShopDtos() {
        return tabletopRepository.findAll().stream().map(this::getShopsBySmartphoneIdDto).collect(Collectors.toList());
    }

    private TabletopBaseShopDto getShopsBySmartphoneIdDto(PlanshetModel model) {
        return new TabletopBaseShopDto(new TabletopBaseDto(model), tabletopPriceService.getShopsByTabletopIdDto(model.getId()));
    }

    @Override
    public TabletopBaseShopDto findOne(Long id) {
        return tabletopRepository.findById(id).map(model -> new TabletopBaseShopDto(new TabletopBaseDto(model), tabletopPriceService.getShopsByTabletopIdDto(id))).get();
    }

    @Override
    public Optional<PlanshetModel> findByName(String name) {
        return tabletopRepository.findByName(name);
    }

    @Override
    public Page<PlanshetModel> findAllPageable(Pageable pageable) {
        return tabletopRepository.findAllPageable(pageable);
    }

    @Override
    public Page<TabletopBaseDto> findAllPageableDto(Pageable pageable) {
        return findAllPageable(pageable).map(TabletopBaseDto::new);
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
