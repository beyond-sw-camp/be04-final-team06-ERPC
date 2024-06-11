package com.cineverse.erpc.file.aggregate;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_quotation_file")
@Builder
public class QuotationFile {

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
    @JoinColumn(name = "quotation_id")
    @JsonIgnore
    private Quotation quotation;

}
