package com.cineverse.erpc.excel;

import com.cineverse.erpc.contract.dto.ContractProductDTO;
import com.cineverse.erpc.order.order.aggregate.OrderProduct;
import com.cineverse.erpc.order.order.dto.OrderDTO;
import com.cineverse.erpc.order.order.service.OrderService;
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
@RequestMapping("excel")
public class OrderExcelController {

    private final OrderService orderService;

    @Autowired
    public OrderExcelController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{orderRegistrationId}")
    public void orderDownload(@PathVariable long orderRegistrationId, HttpServletResponse response) throws IOException {

        OrderDTO order = orderService.findOrderById(orderRegistrationId);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Order Detail");

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
                "거래코드", "거래처명",
                "상품명", "상품수량", "상품단가", "출하창고",
                "수주금액", "계약금", "중도금", "잔금",
                "납기기한일", "계약일", "출고일", "수령일", "담당자", "비고", "작성일자"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(order.getTransaction().getTransactionCode());
        row.createCell(1).setCellValue(order.getAccount().getAccountName());

        int rowNum = 2;
        for (OrderProduct product : order.getOrderProduct()) {
            Row productRow = sheet.createRow(rowNum++);
            productRow.createCell(2).setCellValue(product.getProduct().getProductName());
            productRow.createCell(3).setCellValue(product.getOrderProductCount());
            productRow.createCell(4).setCellValue(product.getOrderSupplyPrice());
            productRow.createCell(5).setCellValue(order.getWarehouse().getWarehouseName());
        }

        row.createCell(6).setCellValue(order.getOrderTotalPrice());
        row.createCell(7).setCellValue(order.getDownPayment());
        row.createCell(8).setCellValue(order.getProgressPayment());
        row.createCell(9).setCellValue(order.getBalance());
        row.createCell(10).setCellValue(order.getOrderDueDate());
        row.createCell(11).setCellValue(order.getContactDate());
        row.createCell(12).setCellValue(order.getReleaseDate());
        row.createCell(13).setCellValue(order.getArriveDate());
        row.createCell(14).setCellValue(order.getEmployee().getEmployeeName());
        row.createCell(15).setCellValue(order.getOrderNote());
        row.createCell(16).setCellValue(order.getOrderDate());

        for (int k = 0; k < headers.length; k++) {
            sheet.autoSizeColumn(k);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String filename = order.getTransaction().getTransactionCode() + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        wb.write(response.getOutputStream());
        wb.close();
    }
}
