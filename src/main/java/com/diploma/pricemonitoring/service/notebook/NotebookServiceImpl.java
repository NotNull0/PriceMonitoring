package com.diploma.pricemonitoring.service.notebook;

import com.diploma.pricemonitoring.model.notebook.NotebookModel;
import com.diploma.pricemonitoring.model.notebook.NotebookPriceModel;
import com.diploma.pricemonitoring.parse.dto.notebooks.NotebookDto;
import com.diploma.pricemonitoring.parse.dto.notebooks.PriceDto;
import com.diploma.pricemonitoring.repository.notebook.NotebookRepository;
import com.diploma.pricemonitoring.service.notebook.interf.NotebookPriceService;
import com.diploma.pricemonitoring.service.notebook.interf.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotebookServiceImpl implements NotebookService {
    @Autowired
    private NotebookRepository notebookRepository;
    @Autowired
    private NotebookPriceService notebookPriceService;

    @Override
    public Optional<NotebookModel> findByName(String name) {
        return notebookRepository.findByName(name);
    }

    @Override
    public NotebookModel save(NotebookDto notebookDto) {
        Optional<NotebookModel> optional = findByName(notebookDto.getName());
        NotebookModel model = new NotebookModel(notebookDto);

        try {

            if (optional.isEmpty()) {
                NotebookModel save = notebookRepository.save(model);
                List<NotebookPriceModel> notebookPriceModels = parseListNotebookPriceDtoToModel(notebookDto.getPriceNotebooks(), save);
                this.saveAllNotebookPrices(notebookPriceModels);
                return save;

            } else {
                NotebookModel currentModel = optional.get();
                List<NotebookPriceModel> notebookPriceModels = parseListNotebookPriceDtoToModel(notebookDto.getPriceNotebooks(), currentModel);
                this.saveAllNotebookPrices(notebookPriceModels);
                return notebookRepository.save(currentModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    private void saveAllNotebookPrices(List<NotebookPriceModel> list) {
        list.forEach(model -> {
            notebookPriceService.save(model);
        });
    }

    private List<NotebookPriceModel> parseListNotebookPriceDtoToModel(List<PriceDto> notebookPriceDtos, NotebookModel notebookModel) {
        return notebookPriceDtos.stream().map(priceNotebook -> parseNotebookPriceDtoToModel(priceNotebook, notebookModel)).collect(Collectors.toList());
    }

    private NotebookPriceModel parseNotebookPriceDtoToModel(PriceDto notebookPriceDto, NotebookModel notebookModel) {
        return new NotebookPriceModel(notebookPriceDto).setNotebookModel(notebookModel);
    }
}
