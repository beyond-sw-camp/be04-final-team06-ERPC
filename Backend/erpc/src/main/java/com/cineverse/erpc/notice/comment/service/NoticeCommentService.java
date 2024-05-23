package com.cineverse.erpc.notice.comment.service;

import com.cineverse.erpc.notice.comment.aggregate.NoticeComment;
import com.cineverse.erpc.notice.comment.dto.NoticeCommentDTO;

import java.util.List;

public interface NoticeCommentService {

    NoticeComment registNoticeComment(NoticeCommentDTO noticeComment);

    NoticeComment deleteNoticeComment(long noticeCommentId);

    List<NoticeComment> findNoticeCommentList();

}
