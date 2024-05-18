package com.cineverse.erpc.slip.taxinvoice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TaxInvoiceFileDTO {
    private long fileId;
    private String originName;
    private String accessUrl;
    private String uploadDate;
    private String uploadUser;
    private long taxInvoiceRequestId;
}
