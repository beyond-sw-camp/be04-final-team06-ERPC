package com.cineverse.erpc.access.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_access_right")
public class AccessRight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    private int accessId;

    @Column(name = "access_right")
    private String accessRight;
}
