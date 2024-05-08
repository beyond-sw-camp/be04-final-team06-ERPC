package com.cineverse.erpc.notice.board.aggregate.entity;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.Team;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="department_notice")
@Builder
public class NoticeBoard {
    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "notice_content")
    private String noticeContent;

    @Column(name = "notice_date")
    private String noticeDate;

    @Column(name = "notice_delete_date")
    private String noticeDeleteDate;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "team_code_id")
    @ManyToOne
    private Team team;
}
