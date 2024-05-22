package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {

    List<NoticeFile> findByNotice_NoticeId(long noticeId);

//    void deleteByNotice_NoticeId(long noticeId);
}
