package com.cineverse.erpc.notice.comment.controller;

import com.cineverse.erpc.notice.comment.aggregate.NoticeComment;
import com.cineverse.erpc.notice.comment.dto.NoticeCommentDTO;
import com.cineverse.erpc.notice.comment.service.NoticeCommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice_comment")
public class NoticeCommentController {

    private final NoticeCommentService noticeCommentService;
    private final ModelMapper modelMapper;

    @Autowired
    public NoticeCommentController(NoticeCommentService noticeCommentService, ModelMapper modelMapper) {
        this.noticeCommentService = noticeCommentService;
        this.modelMapper = modelMapper;
    }

    /* 공지사항 댓글 작성 */
    @PostMapping("/regist")
    public ResponseEntity<NoticeCommentDTO> registNoticeComment(@RequestBody NoticeCommentDTO noticeCommentDTO) {

        noticeCommentService.registNoticeComment(noticeCommentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 공지사항 댓글 삭제 */
    @PatchMapping("/delete/{noticeCommentId}")
    public ResponseEntity<NoticeComment> deleteNoticeComment(@PathVariable long noticeCommentId) {

        return ResponseEntity.ok(noticeCommentService.deleteNoticeComment(noticeCommentId));
    }

    /* 공지사항 댓글 전체 조회 */
    @GetMapping("")
    public List<NoticeComment> findNoticeCommentList() {
        List<NoticeComment> noticeCommentList = noticeCommentService.findNoticeCommentList();

        return noticeCommentList;
    }
}
