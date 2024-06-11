package com.cineverse.erpc.sales.service;

import com.cineverse.erpc.sales.dto.SalesDTO;

public interface SalesService {

    Long calculateIndividualSales(long employeeId);

    Long calculateTeamSales(int teamCodeId);

    Long calculateTotalSales();

    Long calculateTeamMonthlySales(int teamCodeId, int year, int month);

    SalesDTO calculateTeamYearlySales(int teamCodeId);

    SalesDTO calculateEmployeeYearlySales(long employeeId);

    SalesDTO calculateTotalYearlySales();
}
