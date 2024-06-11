package com.cineverse.erpc.notice.comment.controller;

import com.cineverse.erpc.notice.comment.aggregate.NoticeComment;
import com.cineverse.erpc.notice.comment.dto.NoticeCommentDTO;
import com.cineverse.erpc.notice.comment.service.NoticeCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice_comment")
public class NoticeCommentController {

    private final NoticeCommentService noticeCommentService;

    @Autowired
    public NoticeCommentController(NoticeCommentService noticeCommentService) {
        this.noticeCommentService = noticeCommentService;
    }

    @PostMapping("/regist")
    @Operation(summary = "공지사항 댓글 작성", description = "새로운 공지사항 댓글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<NoticeCommentDTO> registNoticeComment(
            @Parameter(description = "공지사항 댓글 데이터", required = true) @RequestBody NoticeCommentDTO noticeCommentDTO) {
        noticeCommentService.registNoticeComment(noticeCommentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/delete/{noticeCommentId}")
    @Operation(summary = "공지사항 댓글 삭제", description = "기존 공지사항 댓글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<NoticeComment> deleteNoticeComment(
            @Parameter(description = "공지사항 댓글 ID", required = true) @PathVariable long noticeCommentId) {
        return ResponseEntity.ok(noticeCommentService.deleteNoticeComment(noticeCommentId));
    }

    @GetMapping("")
    @Operation(summary = "공지사항 댓글 전체 조회", description = "모든 공지사항 댓글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<NoticeComment> findNoticeCommentList() {
        return noticeCommentService.findNoticeCommentList();
    }
}
