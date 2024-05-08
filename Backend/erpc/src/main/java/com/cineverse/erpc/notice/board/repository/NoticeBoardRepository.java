package com.cineverse.erpc.notice.board.repository;

import com.cineverse.erpc.notice.board.aggregate.entity.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
}
