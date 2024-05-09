package com.cineverse.erpc.notice.comment.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NoticeCommentDTO  {
    private Long noticeCommentId;
    private String commentContent;
    private String commentDate;
    private String commentDeleteDate;
    private int noticeId;
    private Employee employee;
}
