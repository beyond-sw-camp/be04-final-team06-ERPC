package com.cineverse.erpc.access.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_access_request")
public class AccessRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_request_id")
    private long accessRequestId;

    @Column(name = "access_request_date")
    private String accessRequestDate;

    @Column(name = "access_request_status")
    private String accessRequestStatus;

    @JoinColumn(name = "access_id")
    @ManyToOne
    private AccessRight accessRight;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;


}
