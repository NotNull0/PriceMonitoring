package com.diploma.pricemonitoring.controller;


import com.diploma.pricemonitoring.service.tabletop.interf.TabletopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tabletop")
public class TabletopController {
    @Autowired
    private TabletopService tabletopService;

    @GetMapping("/find-all")
    public ResponseEntity findAll(Pageable pageable) {
        return ResponseEntity.ok(tabletopService.findAllPageableDto(pageable));
    }
}
