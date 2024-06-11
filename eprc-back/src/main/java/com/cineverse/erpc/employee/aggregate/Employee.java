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
    private long employeeId;

    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_password")
    private String employeePassword;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_hp")
    private String employeeHp;

    @Column(name = "employee_number")
    private String employeeNumber;

    @Column(name = "employment_date")
    private String employmentDate;

    @Column(name = "resignation_date")
    private String resignationDate;

    @JoinColumn(name = "employee_rank_id")
    @ManyToOne
    private EmployeeRank employeeRank;

    @JoinColumn(name = "team_code_id")
    @ManyToOne
    private TeamCode teamCode;

    @Column(name = "employee_uuid")
    private String employeeUUID;
}
