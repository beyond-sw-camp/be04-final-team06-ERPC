package com.cineverse.erpc.notice.board.repository;

import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

    List<NoticeBoard> findByNoticeDeleteDateIsNullOrderByNoticeIdDesc();
}
