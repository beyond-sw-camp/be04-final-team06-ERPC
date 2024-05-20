package com.cineverse.erpc.sales.service;

public interface SalesService {

    Long calculateIndividualSales(long employeeId);

    Long calculateTeamSales(int teamCodeId);

    Long calculateTotalSales();
}
