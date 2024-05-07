package com.cineverse.erpc.employee.aggregate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_employee")
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employId;

    @Column(name = "employee_code")
    private int employCode;

    @Column(name = "employee_password")
    private String employPassword;

    @Column(name = "employee_email")
    private String employEmail;

    @Column(name = "employee_hp")
    private String employHp;

    @Column(name = "employee_number")
    private String employNumber;

    @Column(name = "employee_date")
    private String employDate;

    @Column(name = "resignation_date")
    private String resignationDate;

    @Column(name = "employee_rank_id")
    private int employRankId;

    @Column(name = "team_code_id")
    private int teamCodeId;

    @Column(name = "employee_uuid")
    private String employeeUUID;
}
