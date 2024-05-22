package com.cineverse.erpc.notice.board.service;

import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoticeBoardService {

    NoticeBoard registNotice(NoticeBoardDTO newNotice, MultipartFile[] files);

    NoticeBoard modifyNotice(Long noticeId, NoticeBoardDTO notice);

    NoticeBoard deleteNotice(Long noticeId);

    List<NoticeBoard> findNoticeList();

    NoticeBoardDTO findNoticeById(Long noticeId);

}
