package com.cineverse.erpc.target.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.Team;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TargetDTO {
    private long targetId;
    private long targetPrice;
    private String targetYear;
    private String targetQuarter;
    private String targetMonth;
    private Team team;
    private Employee employee;
}
