package com.cineverse.erpc.notice.comment.service;

import com.cineverse.erpc.notice.comment.aggregate.entity.NoticeComment;
import com.cineverse.erpc.notice.comment.dto.NoticeCommentDTO;

import java.util.List;

public interface NoticeCommentService {

    NoticeComment registNoticeComment(NoticeCommentDTO noticeComment);

    NoticeComment deleteNoticeComment(Long noticeCommentId);

    List<NoticeComment> findNoticeCommentList();

}
