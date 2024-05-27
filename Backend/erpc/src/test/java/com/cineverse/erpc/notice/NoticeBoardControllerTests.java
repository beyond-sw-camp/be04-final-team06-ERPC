package com.cineverse.erpc.notice;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;
import com.cineverse.erpc.notice.board.repository.NoticeBoardRepository;
import com.cineverse.erpc.notice.board.service.NoticeBoardService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NoticeBoardControllerTests {

    private final NoticeBoardService noticeBoardService;
    private final NoticeBoardRepository noticeBoardRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public NoticeBoardControllerTests(NoticeBoardService noticeBoardService,
                                      NoticeBoardRepository noticeBoardRepository,
                                      EmployeeRepository employeeRepository) {
        this.noticeBoardService = noticeBoardService;
        this.noticeBoardRepository = noticeBoardRepository;
        this.employeeRepository = employeeRepository;
    }

    @Test
    @Transactional
    @DisplayName("공지사항 등록 테스트")
    public void registNotice() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));

        TeamCode team = new TeamCode();
        team.setTeamCodeId(1);
        team.setTeamCode("영업 1팀");

        NoticeBoardDTO testNotice = NoticeBoardDTO.builder()
                .noticeTitle("테스트 공지사항 제목")
                .noticeContent("테스트 공지사항 내용")
                .employee(employee)
                .team(team)
                .build();
        MultipartFile[] files = new MultipartFile[0];

        NoticeBoard notice = noticeBoardService.registNotice(testNotice, files);

        assertThat(notice.getNoticeTitle()).isEqualTo(testNotice.getNoticeTitle());
        assertThat(notice.getNoticeContent()).isEqualTo(testNotice.getNoticeContent());
        assertThat(notice.getNoticeDate()).isNotNull();
        assertThat(notice.getEmployee()).isEqualTo(testNotice.getEmployee());
        assertThat(notice.getTeam()).isEqualTo(testNotice.getTeam());
    }

    @Test
    @Transactional
    @DisplayName("공지사항 수정 테스트")
    public void modifyNotice() {
        NoticeBoard existingNotice = noticeBoardRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 공지사항입니다."));

        NoticeBoardDTO updatedNotice = NoticeBoardDTO.builder()
                .noticeTitle("(수정) 공지사항 제목")
                .noticeContent("(수정) 공지사항 내용")
                .build();
        MultipartFile[] files = new MultipartFile[0];

        NoticeBoard updatedNoticeBoard = noticeBoardService.modifyNotice(1, updatedNotice, files);

        assertThat(updatedNoticeBoard.getNoticeTitle()).isEqualTo(updatedNotice.getNoticeTitle());
        assertThat(updatedNoticeBoard.getNoticeContent()).isEqualTo(updatedNotice.getNoticeContent());
    }

    @Test
    @Transactional
    @DisplayName("공지사항 삭제 테스트")
    void deleteNotice() {
        NoticeBoard notice = noticeBoardRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 공지사항입니다."));
        assertThat(notice.getNoticeDeleteDate()).isNull();

        NoticeBoard deletedNotice = noticeBoardService.deleteNotice(1);
        assertThat(deletedNotice.getNoticeDeleteDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("공지사항 전체 조회 테스트")
    public void findNoticeList() {
        List<NoticeBoard> actualNoticeList = noticeBoardRepository.findByNoticeDeleteDateIsNullOrderByNoticeIdDesc();
        List<NoticeBoard> testNoticeList = noticeBoardService.findNoticeList();

        assertThat(actualNoticeList.size()).isEqualTo(testNoticeList.size());
        assertThat(testNoticeList).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("공지사항 단일 조회 테스트")
    void findNoticeById() {
        NoticeBoard testNotice = noticeBoardRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 공지사항입니다"));

        NoticeBoardDTO notice = noticeBoardService.findNoticeById(2);

        assertThat(notice.getNoticeTitle()).isEqualTo(testNotice.getNoticeTitle());
        assertThat(notice.getNoticeContent()).isEqualTo(testNotice.getNoticeContent());
        assertThat(notice.getNoticeDate()).isNotNull();
        assertThat(notice.getEmployee()).isEqualTo(testNotice.getEmployee());
        assertThat(notice.getTeam()).isEqualTo(testNotice.getTeam());
    }

}