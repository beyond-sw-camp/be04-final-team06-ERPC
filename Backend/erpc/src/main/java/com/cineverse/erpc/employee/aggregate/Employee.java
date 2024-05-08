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
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "employee_password")
    private String employeePassword;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_hp")
    private String employeeHp;

    @Column(name = "employee_number")
    private String employeeNumber;

    @Column(name = "employee_date")
    private String employeeDate;

    @Column(name = "resignation_date")
    private String resignationDate;

    @Column(name = "employee_rank_id")
    private int employeeRankId;

    @Column(name = "team_code_id")
    private int teamCodeId;

    @Column(name = "employee_uuid")
    private String employeeUUID;
}
