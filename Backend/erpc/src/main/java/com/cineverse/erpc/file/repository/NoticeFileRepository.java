package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {
}
