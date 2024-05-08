package com.cineverse.erpc.notice.board.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.Team;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NoticeBoardDTO {
    private int noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;
    private String noticeDeleteDate;
    private Employee employee;
    private Team team;
}