package com.cineverse.erpc.notice;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.notice.board.repository.NoticeBoardRepository;
import com.cineverse.erpc.notice.comment.aggregate.NoticeComment;
import com.cineverse.erpc.notice.comment.dto.NoticeCommentDTO;
import com.cineverse.erpc.notice.comment.repository.NoticeCommentRepository;
import com.cineverse.erpc.notice.comment.service.NoticeCommentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoticeCommentControllerTests {

    private final NoticeCommentRepository noticeCommentRepository;
    private final NoticeCommentService noticeCommentService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public NoticeCommentControllerTests(NoticeCommentRepository noticeCommentRepository,
                                        NoticeBoardRepository noticeBoardRepository,
                                        NoticeCommentService noticeCommentService,
                                        EmployeeRepository employeeRepository) {
        this.noticeCommentRepository = noticeCommentRepository;
        this.noticeCommentService = noticeCommentService;
        this.employeeRepository = employeeRepository;
    }

    @Test
    @Transactional
    @DisplayName("공지사항 댓글 작성 테스트")
    public void registNoticeComment() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));

        NoticeCommentDTO testNoticeComment = NoticeCommentDTO.builder()
                .commentContent("테스트 공지사항 제목")
                .noticeId(1)
                .employee(employee)
                .build();

        NoticeComment noticeComment = noticeCommentService.registNoticeComment(testNoticeComment);

        assertThat(noticeComment.getCommentContent()).isEqualTo(testNoticeComment.getCommentContent());
        assertThat(noticeComment.getNoticeId()).isEqualTo(1);
        assertThat(noticeComment.getCommentDate()).isNotNull();
        assertThat(noticeComment.getEmployee()).isEqualTo(testNoticeComment.getEmployee());
    }

    @Test
    @Transactional
    @DisplayName("공지사항 댓글 삭제 테스트")
    public void deleteNoticeComment() {
        NoticeComment comment = noticeCommentRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 공지사항 댓글입니다."));
        assertThat(comment.getCommentDeleteDate()).isNull();

        NoticeComment deletedComment = noticeCommentService.deleteNoticeComment(Long.valueOf(1));
        assertThat(deletedComment.getCommentDeleteDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("공지사항 댓글 전체 조회 테스트")
    public void findNoticeCommentList() {
        List<NoticeComment> actualCommentList = noticeCommentRepository.findByCommentDeleteDateIsNullOrderByNoticeCommentIdDesc();
        List<NoticeComment> testCommentList = noticeCommentService.findNoticeCommentList();

        assertThat(actualCommentList.size()).isEqualTo(testCommentList.size());
        assertThat(testCommentList).isNotEmpty();
    }
}