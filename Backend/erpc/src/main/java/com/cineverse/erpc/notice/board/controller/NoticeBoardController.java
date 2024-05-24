package com.cineverse.erpc.notice.board.controller;

import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;
import com.cineverse.erpc.notice.board.service.NoticeBoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
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
    public ResponseEntity<NoticeBoardDTO> registNotice(@RequestPart("noticeBoard") String noticeJson,
                                                       @RequestPart(value = "files", required = false) MultipartFile[] files)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        NoticeBoardDTO newNotice = objectMapper.readValue(noticeJson, NoticeBoardDTO.class);

        noticeBoardService.registNotice(newNotice, files);

        return ResponseEntity.status(HttpStatus.CREATED).body(newNotice);
    }

    /* 공지사항 게시글 수정 */
    @PatchMapping(path = "/modify/{noticeId}", consumes = {"multipart/form-data;charset=UTF-8"})
    public ResponseEntity<NoticeBoard> modifyNotice(@RequestPart("notice") String noticeJson,
                                                    @RequestPart(value = "files", required = false) MultipartFile[] files,
                                                    @PathVariable long noticeId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        NoticeBoardDTO notice = objectMapper.readValue(noticeJson, NoticeBoardDTO.class);

        noticeBoardService.modifyNotice(noticeId, notice, files);

        return ResponseEntity.ok().build();
    }

    /* 공지사항 게시글 삭제 */
    @PatchMapping("/delete/{noticeId}")
    public ResponseEntity<NoticeBoard> deleteNotice(@PathVariable long noticeId) {
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
