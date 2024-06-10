package com.cineverse.erpc.notice.board.service;

import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoticeBoardService {

    NoticeBoard registNotice(NoticeBoardDTO newNotice, MultipartFile[] files);

    NoticeBoard modifyNotice(long noticeId, NoticeBoardDTO notice, MultipartFile[] files);

    NoticeBoard deleteNotice(long noticeId);

    List<NoticeBoard> findNoticeList();

    NoticeBoardDTO findNoticeById(long noticeId);

}
