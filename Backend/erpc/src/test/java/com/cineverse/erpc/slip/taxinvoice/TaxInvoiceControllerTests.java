package com.cineverse.erpc.slip.taxinvoice;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaxInvoiceControllerTests {

    @Test
    @Transactional
    @DisplayName("세금계산서 요청 등록 테스트")
    public void registTaxInvoiceRequest() {
    }

    @Test
    @Transactional
    @DisplayName("요청한 세금계산서 전체조회 테스트")
    public void findTaxInvoiceList() {
    }

    @Test
    @Transactional
    @DisplayName("요청한 세금계산서 단일조회 테스트")
    public void findTaxInvoiceRequestById() {
    }

}