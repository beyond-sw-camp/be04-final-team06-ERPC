package com.cineverse.erpc.file.aggregate;


import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_notice_file")
@Builder
public class NoticeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private long fileId;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "stored_name")
    private String storedName;

    @Column(name = "access_url")
    private String accessUrl;

    @Column(name = "upload_date")
    private String uploadDate;

    @ManyToOne
    @JoinColumn(name = "notice_id")
    @JsonIgnore
    private NoticeBoard notice;
}
