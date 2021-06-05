package com.diploma.pricemonitoring.controller;


import com.diploma.pricemonitoring.excel.SmartphoneXlsView;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopPriceService;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/tabletop")
public class TabletopController {
    @Autowired
    private TabletopService tabletopService;
    @Autowired
    private TabletopPriceService tabletopPriceService;

    @GetMapping("/find-all")
    public ResponseEntity findAll(Pageable pageable) {
        return ResponseEntity.ok(tabletopService.findAllPageableDto(pageable));
    }

    @GetMapping("/find-all-shop")
    public ResponseEntity findAll(@RequestParam Long ID) {
        return ResponseEntity.ok(tabletopPriceService.getShopsByTabletopIdDto(ID));
    }

    @GetMapping("/find-one")
    public ResponseEntity findOne(@RequestParam Long ID) {
        return ResponseEntity.ok(tabletopService.findOne(ID));
    }

    @GetMapping("/create-excel")
    public ModelAndView createExcel() {
        ModelAndView modelAndView = new ModelAndView(new SmartphoneXlsView());
        modelAndView.getModel().clear();
        modelAndView.addObject("tabletop_details", tabletopService.getTabletopBaseShopDtos());
        modelAndView.addObject("name", "Tabletop statistic");
        return modelAndView;
    }
}
