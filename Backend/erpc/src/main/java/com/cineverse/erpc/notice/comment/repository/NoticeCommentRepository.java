package com.cineverse.erpc.notice.comment.repository;

import com.cineverse.erpc.notice.comment.aggregate.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> {

    List<NoticeComment> findByCommentDeleteDateIsNullOrderByNoticeCommentIdDesc();
}
