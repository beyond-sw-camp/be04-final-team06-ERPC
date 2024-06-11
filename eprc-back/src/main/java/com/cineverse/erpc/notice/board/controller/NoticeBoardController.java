package com.cineverse.erpc.notice.board.controller;

import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;
import com.cineverse.erpc.notice.board.service.NoticeBoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(path = "/regist", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "공지사항 게시글 작성", description = "새로운 공지사항 게시글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<NoticeBoardDTO> registNotice(
            @Parameter(description = "공지사항 게시글 JSON 데이터", required = true) @RequestPart("noticeBoard") String noticeJson,
            @Parameter(description = "첨부 파일들") @RequestPart(value = "files", required = false) MultipartFile[] files)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        NoticeBoardDTO newNotice = objectMapper.readValue(noticeJson, NoticeBoardDTO.class);
        noticeBoardService.registNotice(newNotice, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(newNotice);
    }

    /* 공지사항 게시글 수정 */
    @PatchMapping(path = "/modify/{noticeId}", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "공지사항 게시글 수정", description = "기존 공지사항 게시글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<NoticeBoard> modifyNotice(
            @Parameter(description = "수정할 공지사항 게시글 JSON 데이터", required = true) @RequestPart("notice") String noticeJson,
            @Parameter(description = "첨부 파일들") @RequestPart(value = "files", required = false) MultipartFile[] files,
            @Parameter(description = "공지사항 게시글 ID", required = true) @PathVariable long noticeId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        NoticeBoardDTO notice = objectMapper.readValue(noticeJson, NoticeBoardDTO.class);
        noticeBoardService.modifyNotice(noticeId, notice, files);
        return ResponseEntity.ok().build();
    }

    /* 공지사항 게시글 삭제 */
    @PatchMapping("/delete/{noticeId}")
    @Operation(summary = "공지사항 게시글 삭제", description = "기존 공지사항 게시글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<NoticeBoard> deleteNotice(
            @Parameter(description = "공지사항 게시글 ID", required = true) @PathVariable long noticeId) {
        return ResponseEntity.ok(noticeBoardService.deleteNotice(noticeId));
    }

    /* 공지사항 전체 조회 */
    @GetMapping("")
    @Operation(summary = "공지사항 전체 조회", description = "모든 공지사항 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<NoticeBoard> findNoticeList() {
        return noticeBoardService.findNoticeList();
    }

    /* 공지사항 단일 조회 */
    @GetMapping("/{noticeId}")
    @Operation(summary = "공지사항 단일 조회", description = "특정 공지사항 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "공지사항 게시글을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public NoticeBoardDTO findNoticeById(
            @Parameter(description = "공지사항 게시글 ID", required = true) @PathVariable Long noticeId) {
        return noticeBoardService.findNoticeById(noticeId);
    }
}
