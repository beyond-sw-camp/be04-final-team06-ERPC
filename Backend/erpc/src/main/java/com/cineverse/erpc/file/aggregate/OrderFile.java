package com.cineverse.erpc.file.aggregate;

import com.cineverse.erpc.order.order.aggregate.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_order_file")
public class OrderFile {

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

    @Column(name = "upload_user")
    private String uploadUser;

    @ManyToOne
    @JoinColumn(name = "order_registration_id")
    @JsonIgnore
    private Order order;
}
