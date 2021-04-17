package com.diploma.pricemonitoring.controller;


import com.diploma.pricemonitoring.service.tabletop.interf.TabletopPriceService;
import com.diploma.pricemonitoring.service.tabletop.interf.TabletopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity findAll(@RequestParam Long tabletopId) {
        return ResponseEntity.ok(tabletopPriceService.getShopsByTabletopIdDto(tabletopId));
    }


}
