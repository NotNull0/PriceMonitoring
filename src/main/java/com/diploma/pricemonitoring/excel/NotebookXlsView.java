package com.diploma.pricemonitoring.excel;

import com.diploma.pricemonitoring.dto.NotebookBaseShopDto;
import com.diploma.pricemonitoring.dto.NotebookShopDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Slf4j
public class NotebookXlsView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {
        String  name = (String)map.get("name");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + name + ".xls\"");
        List<NotebookBaseShopDto> notebooks = (List<NotebookBaseShopDto>) map.get("notebook_details");

        Sheet sheet = workbook.createSheet("Notebooks");
        sheet.setDefaultColumnWidth(30);

        Row row = sheet.createRow(0);
        int indexRow = 1;


        for (int i = 0; i < notebooks.size(); i++) {
            List<String> notebookAttributes = List.of("Назва", "Дисплей", "Процесор", "Память", "Внутрішня память", "Вага", "Опис");
            createHeaderRow(notebookAttributes, row, workbook);
            row = sheet.createRow(indexRow++);
            NotebookBaseShopDto dto = notebooks.get(i);

            row.createCell(0).setCellValue(dto.getDetails().getName());
            row.createCell(1).setCellValue(dto.getDetails().getDisplay());
            row.createCell(2).setCellValue(dto.getDetails().getProcessor());
            row.createCell(3).setCellValue(dto.getDetails().getOZU());
            row.createCell(4).setCellValue(dto.getDetails().getStorage());
            row.createCell(5).setCellValue(dto.getDetails().getWeight());
            row.createCell(6).setCellValue(dto.getDetails().getDescription());
            row = sheet.createRow(indexRow++);

            List<NotebookShopDto> list = dto.getList();
            logger.info(notebooks.get(i).getDetails().toString());
            List<String> shopDetails = List.of("Магазин", "Ціна");
            createHeaderRow(shopDetails, row, workbook);
            for (int j = 0; j < list.size(); j++) {
                row = sheet.createRow(indexRow++);
                row.createCell(0).setCellValue(list.get(j).getShop().toString());
                row.createCell(1).setCellValue(list.get(j).getPrice());
            }
            row = sheet.createRow(indexRow++); row = sheet.createRow(indexRow++); row = sheet.createRow(indexRow++);
        }


    }


    private void createHeaderRow(List<String> args, Row row, Workbook workbook) {
        for (int i = 0; i < args.size(); i++) {
            row.createCell(i).setCellValue(args.get(i));
            row.getCell(i).setCellStyle(getHeaderStyle(workbook));
        }
    }

    private CellStyle getHeaderStyle(Workbook workbook) {
        Font fontBold = workbook.createFont();
        fontBold.setFontName("Arial");
        fontBold.setBold(true);
        fontBold.setColor(HSSFColor.BLACK.index);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFont(fontBold);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        return headerStyle;
    }
}
