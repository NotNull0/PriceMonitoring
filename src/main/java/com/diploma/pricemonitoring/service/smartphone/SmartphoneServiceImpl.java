package com.diploma.pricemonitoring.service.smartphone;

import com.diploma.pricemonitoring.dto.smartphone.SmartphoneBaseDto;
import com.diploma.pricemonitoring.model.smartphone.SmartphoneModel;
import com.diploma.pricemonitoring.model.smartphone.SmartphonePriceModel;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;
import com.diploma.pricemonitoring.parse.dto.smartphones.SmartphoneDto;
import com.diploma.pricemonitoring.repository.smartphone.SmartphoneRepository;
import com.diploma.pricemonitoring.service.smartphone.interf.SmartphonePriceService;
import com.diploma.pricemonitoring.service.smartphone.interf.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {
    @Autowired
    private SmartphoneRepository smartphoneRepository;
    @Autowired
    private SmartphonePriceService smartphonePriceService;

    @Override
    public Optional<SmartphoneModel> findByName(String name) {
        return smartphoneRepository.findByName(name);
    }

    @Override
    public Page<SmartphoneModel> findAllPageable(Pageable pageable) {
        return smartphoneRepository.findAllPageable(pageable);
    }

    @Override
    public Page<SmartphoneBaseDto> findAllPageableBaseModel(Pageable pageable) {
        return findAllPageable(pageable).map(SmartphoneBaseDto::new);
    }

    @Override
    public SmartphoneModel save(SmartphoneDto smartphoneDto) {
        Optional<SmartphoneModel> optional = findByName(smartphoneDto.getName());
        SmartphoneModel model = new SmartphoneModel(smartphoneDto);

        try {

            if (optional.isEmpty()) {
                SmartphoneModel save = smartphoneRepository.save(model);
                List<SmartphonePriceModel> smartphonePriceModels = parseListNotebookPriceDtoToModel(smartphoneDto.getPriceDto(), save);
                this.saveAllNotebookPrices(smartphonePriceModels);
                return save;

            } else {
                SmartphoneModel currentModel = optional.get();
                List<SmartphonePriceModel> smartphonePriceModels = parseListNotebookPriceDtoToModel(smartphoneDto.getPriceDto(), currentModel);
                this.saveAllNotebookPrices(smartphonePriceModels);
                return smartphoneRepository.save(currentModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    private void saveAllNotebookPrices(List<SmartphonePriceModel> list) {
        list.forEach(model -> {
            smartphonePriceService.save(model);
        });
    }

    private List<SmartphonePriceModel> parseListNotebookPriceDtoToModel(List<PriceDto> priceDtos, SmartphoneModel smartphoneModel) {
        return priceDtos.stream().map(priceNotebook -> parseNotebookPriceDtoToModel(priceNotebook, smartphoneModel)).collect(Collectors.toList());
    }

    private SmartphonePriceModel parseNotebookPriceDtoToModel(PriceDto priceDto, SmartphoneModel smartphoneModel) {
        return new SmartphonePriceModel(priceDto).setSmartphoneModel(smartphoneModel);
    }
}
