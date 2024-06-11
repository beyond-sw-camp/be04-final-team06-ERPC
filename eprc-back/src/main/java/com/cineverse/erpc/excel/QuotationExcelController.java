package com.cineverse.erpc.excel;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.aggregate.QuotationProduct;
import com.cineverse.erpc.quotation.quotation.service.QuotationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class QuotationExcelController {

    private final QuotationService quotationService;

    @Autowired
    public QuotationExcelController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @GetMapping("/quotation/{quotationId}")
    @Operation(summary = "견적서 엑셀 다운로드", description = "지정된 견적서 ID의 엑셀 파일을 다운로드합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "다운로드 성공"),
            @ApiResponse(responseCode = "404", description = "견적서를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public void quotationDownload(
            @Parameter(description = "견적서 ID", required = true) @PathVariable long quotationId,
            HttpServletResponse response) throws IOException {

        Quotation quotation = quotationService.findQuotationById(quotationId);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Quotation Detail");

        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        CellStyle bodyStyle = wb.createCellStyle();
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);

        String[] headers = {
                "견적서코드", "거래처명", "거래번호",
                "상품명", "상품수량", "상품단가",
                "총비용", "마감일자", "담당자명", "비고", "작성일자"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(quotation.getQuotationCode());
        row.createCell(1).setCellValue(quotation.getAccount().getAccountName());
        row.createCell(2).setCellValue(quotation.getTransaction().getTransactionCode());

        int rowNum = 2;
        for (QuotationProduct product : quotation.getQuotationProduct()) {
            Row productRow = sheet.createRow(rowNum++);
            productRow.createCell(3).setCellValue(product.getProduct().getProductId());
            productRow.createCell(4).setCellValue(product.getQuotationProductCount());
            productRow.createCell(5).setCellValue(product.getQuotationSupplyPrice());
        }

        row.createCell(6).setCellValue(quotation.getQuotationTotalCost());
        row.createCell(7).setCellValue(quotation.getQuotationDueDate());
        row.createCell(8).setCellValue(quotation.getEmployee().getEmployeeName());
        row.createCell(9).setCellValue(quotation.getQuotationNote());
        row.createCell(10).setCellValue(quotation.getQuotationDate());

        for (int k = 0; k < headers.length; k++) {
            sheet.autoSizeColumn(k);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String filename = quotation.getQuotationCode() + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        wb.write(response.getOutputStream());
        wb.close();
    }
}
