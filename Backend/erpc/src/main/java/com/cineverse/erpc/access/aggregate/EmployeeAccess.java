package com.cineverse.erpc.access.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_employee_access")
public class EmployeeAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_access_id")
    private long employeeAccessId;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "access_id")
    @ManyToOne
    private AccessRight accessRight;
}
