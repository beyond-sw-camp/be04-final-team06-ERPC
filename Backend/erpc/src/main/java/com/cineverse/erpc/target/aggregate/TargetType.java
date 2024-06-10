package com.cineverse.erpc.target.aggregate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_target_type")
public class TargetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_type_id")
    private int targetTypeId;

    @Column(name = "target_type")
    private String targetType;
}
