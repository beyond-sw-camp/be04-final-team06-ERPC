package com.cineverse.erpc.notice.board.service;

import com.cineverse.erpc.notice.board.aggregate.entity.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;

import java.util.List;

public interface NoticeBoardService {

    NoticeBoard registNotice(NoticeBoardDTO newNotice);

    NoticeBoard modifyNotice(Long noticeId, NoticeBoardDTO notice);

    NoticeBoard deleteNotice(Long noticeId);

    List<NoticeBoard> findNoticeList();

    NoticeBoardDTO findNoticeById(Long noticeId);
}
