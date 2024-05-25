package com.cineverse.erpc.excel;

import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractProductDTO;
import com.cineverse.erpc.contract.service.ContractService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("excel")
public class ContractExcelController {

    private final ContractService contractService;

    @Autowired
    public ContractExcelController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("contract/{contractId}")
    public void contractDownload(@PathVariable long contractId, HttpServletResponse response) throws IOException {
        ContractDTO contract = contractService.findContractById(contractId);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Contract Detail");

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
                "계약서 코드", "거래처명", "거래코드",
                "상품명", "상품수량", "상품단가",
                "납부형식", "총비용", "계약금", "중도금", "잔금",
                "마감일자", "담당자명", "비고", "작성일자"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(contract.getContractCode());
        row.createCell(1).setCellValue(contract.getAccount().getAccountName());
        row.createCell(2).setCellValue(contract.getTransaction().getTransactionCode());

        int rowNum = 2;
        for (ContractProductDTO product : contract.getContractProduct()) {
            Row productRow = sheet.createRow(rowNum++);
            productRow.createCell(3).setCellValue(product.getProduct().getProductName());
            productRow.createCell(4).setCellValue(product.getContractProductCount());
            productRow.createCell(5).setCellValue(product.getContractSupplyPrice());
        }

        row.createCell(6).setCellValue(contract.getContractCategory().getContractCategory());
        row.createCell(7).setCellValue(contract.getContractTotalPrice());

        Cell downPaymentCell = row.createCell(8);
        if (contract.getDownPayment() != null) {
            downPaymentCell.setCellValue(contract.getDownPayment());
        } else {
            downPaymentCell.setCellValue("");
        }

        Cell progressPaymentCell = row.createCell(9);
        if (contract.getProgressPayment() != null) {
            progressPaymentCell.setCellValue(contract.getProgressPayment());
        } else {
            progressPaymentCell.setCellValue("");
        }

        Cell balanceCell = row.createCell(10);
        if (contract.getBalance() != null) {
            balanceCell.setCellValue(contract.getBalance());
        } else {
            balanceCell.setCellValue("");
        }

        row.createCell(11).setCellValue(contract.getContractDueDate());
        row.createCell(12).setCellValue(contract.getEmployee().getEmployeeName());
        row.createCell(13).setCellValue(contract.getContractNote());
        row.createCell(14).setCellValue(contract.getContractDate());

        for (int k = 0; k < headers.length; k++) {
            sheet.autoSizeColumn(k);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String filename = contract.getContractCode() + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        wb.write(response.getOutputStream());
        wb.close();
    }
}