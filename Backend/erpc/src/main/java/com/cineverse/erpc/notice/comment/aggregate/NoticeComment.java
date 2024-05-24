package com.cineverse.erpc.notice.comment.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_notice_comment")
@Builder
public class NoticeComment {
    @Id
    @Column(name = "notice_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeCommentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_date")
    private String commentDate;

    @Column(name = "comment_delete_date")
    private String commentDeleteDate;

    @Column(name = "notice_id")
    private int noticeId;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;
}
