package com.cineverse.erpc.notice.board.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.Team;
import com.cineverse.erpc.file.aggregate.NoticeFile;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NoticeBoardDTO {
    private long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;
    private String noticeDeleteDate;
    private Employee employee;
    private Team team;
    private List<NoticeFile> noticeFile;
}