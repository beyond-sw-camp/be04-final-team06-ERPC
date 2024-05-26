package com.cineverse.erpc.target.aggregate;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_target")
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_id")
    private long targetId;

    @Column(name = "target_price")
    private long targetPrice;

    @Column(name = "target_year")
    private String targetYear;

    @Column(name = "target_quarter")
    private String targetQuarter;

    @Column(name = "target_month")
    private String targetMonth;

    @JoinColumn(name = "employee_id")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "team_code_id")
    @ManyToOne
    private TeamCode team;
}
