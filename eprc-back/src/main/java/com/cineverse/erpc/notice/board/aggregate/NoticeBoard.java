package com.cineverse.erpc.notice.board.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import com.cineverse.erpc.file.aggregate.NoticeFile;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_department_notice")
@Builder
public class NoticeBoard {
    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeId;

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
    private TeamCode team;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NoticeFile> noticeFile = new ArrayList<>();
}
