package com.cineverse.erpc.notice.board.service;

import com.cineverse.erpc.notice.board.aggregate.entity.NoticeBoard;
import com.cineverse.erpc.notice.board.dto.NoticeBoardDTO;

public interface NoticeBoardService {
    NoticeBoard registNotice(NoticeBoardDTO newNotice);

    NoticeBoard modifyNotice(Long noticeId, NoticeBoardDTO notice);

    NoticeBoard deleteNotice(Long noticeId);
}
