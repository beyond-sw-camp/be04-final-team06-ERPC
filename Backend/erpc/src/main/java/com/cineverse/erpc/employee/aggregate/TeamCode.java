package com.cineverse.erpc.employee.aggregate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_team_code")
@Builder
public class TeamCode {
    @Id
    @Column(name = "team_code_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamCodeId;

    @Column(name = "team_code")
    private String teamCode;
}
