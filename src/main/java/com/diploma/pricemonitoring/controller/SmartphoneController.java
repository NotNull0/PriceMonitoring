package com.diploma.pricemonitoring.controller;

import com.diploma.pricemonitoring.excel.NotebookXlsView;
import com.diploma.pricemonitoring.excel.SmartphoneXlsView;
import com.diploma.pricemonitoring.service.smartphone.interf.SmartphonePriceService;
import com.diploma.pricemonitoring.service.smartphone.interf.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/smartphone")
public class SmartphoneController {
    @Autowired
    private SmartphoneService smartphoneService;
    @Autowired
    private SmartphonePriceService smartphonePriceService;

    @GetMapping("/find-all")
    public ResponseEntity findAll(Pageable pageable) {
        return ResponseEntity.ok(smartphoneService.findAllPageableBaseModel(pageable));
    }

    @GetMapping("/find-all-shop")
    public ResponseEntity findAll(@RequestParam Long ID) {
        return ResponseEntity.ok(smartphonePriceService.getShopsBySmartphoneIdDto(ID));
    }

    @GetMapping("/find-one")
    public ResponseEntity findOne(@RequestParam Long ID) {
        return ResponseEntity.ok(smartphoneService.findOne(ID));
    }

    @GetMapping("/create-excel")
    public ModelAndView createExcel() {
        ModelAndView modelAndView = new ModelAndView(new SmartphoneXlsView());
        modelAndView.getModel().clear();
        modelAndView.addObject("smartphone_details", smartphoneService.getSmartphoneBaseShopDto());
        modelAndView.addObject("name", "Smartphone statistic");
        return modelAndView;
    }
}
