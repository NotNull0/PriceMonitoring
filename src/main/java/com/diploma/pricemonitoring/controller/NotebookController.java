package com.diploma.pricemonitoring.controller;

import com.diploma.pricemonitoring.excel.NotebookXlsView;
import com.diploma.pricemonitoring.service.notebook.interf.NotebookPriceService;
import com.diploma.pricemonitoring.service.notebook.interf.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/notebook")
public class NotebookController {
    @Autowired
    private NotebookService notebookService;
    @Autowired
    private NotebookPriceService notebookPriceService;

    @GetMapping("/find-all")
    public ResponseEntity findAll(Pageable pageable) {
        return ResponseEntity.ok(notebookService.findAllPageableBaseModel(pageable));
    }

    @GetMapping("/find-one")
    public ResponseEntity findOne(@RequestParam Long ID) {
        return ResponseEntity.ok(notebookService.findOne(ID));
    }

    @GetMapping("/find-all-shop")
    public ResponseEntity findAll(@RequestParam Long ID) {
        return ResponseEntity.ok(notebookPriceService.getShopsByNotebookIdDto(ID));
    }

    @GetMapping("/create-excel")
    public ModelAndView createExcel() {
        ModelAndView modelAndView = new ModelAndView(new NotebookXlsView());
        modelAndView.getModel().clear();
        modelAndView.addObject("notebook_details", notebookService.getNotebooksDetails());
        modelAndView.addObject("name", "Notebooks statistic");
        return modelAndView;
    }
}
