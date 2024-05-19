package com.cineverse.erpc.notice.board.controller;

import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;
import com.cineverse.erpc.notice.board.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice_board")
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    @Autowired
    public NoticeBoardController(NoticeBoardService noticeBoardService) {
        this.noticeBoardService = noticeBoardService;
    }

    /* 공지사항 게시글 작성 */
    @PostMapping("/regist")
    public ResponseEntity<NoticeBoardDTO> registNotice(@RequestBody NoticeBoardDTO newNotice) {
        noticeBoardService.registNotice(newNotice);
        return ResponseEntity.status(HttpStatus.CREATED).body(newNotice);
    }

    /* 공지사항 게시글 수정 */
    @PatchMapping("/modify/{noticeId}")
    public ResponseEntity<NoticeBoard> modifyNotice(@RequestBody NoticeBoardDTO notice, @PathVariable Long noticeId) {
        return ResponseEntity.ok(noticeBoardService.modifyNotice(noticeId, notice));
    }

    /* 공지사항 게시글 삭제 */
    @PatchMapping("/delete/{noticeId}")
    public ResponseEntity<NoticeBoard> deleteNotice(@PathVariable Long noticeId) {
        return ResponseEntity.ok(noticeBoardService.deleteNotice(noticeId));
    }

    /* 공지사항 전체 조회 */
    @GetMapping("")
    public List<NoticeBoard> findNoticeList() {
        List<NoticeBoard> noticeList = noticeBoardService.findNoticeList();

        return noticeList;
    }

    /* 공지사항 단일 조회 */
    @GetMapping("/{noticeId}")
    public NoticeBoardDTO findNoticeById(@PathVariable Long noticeId) {
        NoticeBoardDTO notice = noticeBoardService.findNoticeById(noticeId);

        return notice;
    }
}
