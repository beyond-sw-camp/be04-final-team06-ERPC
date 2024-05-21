package com.cineverse.smtp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApprovalMessage {
    private String to;
    private String subject;
    private String message;
}
