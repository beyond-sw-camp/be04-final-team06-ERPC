package com.cineverse.erpc.employee.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_employee_rank")
public class EmployeeRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_rank_id")
    private int employeeRankId;

    @Column(name = "employee_rank")
    private String employeeRank;
}
