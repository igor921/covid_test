package org.computools.covid.service;

import org.computools.covid.model.cases.CovidCases;
import org.computools.covid.model.history.HistoryCases;
import org.computools.covid.model.vaccine.VaccineCases;

import java.net.URISyntaxException;
import java.util.concurrent.Future;

//Interface for receiving data from API.
// We can easily switch to another API just implement this interface
public interface ApiClientInterface {

    Future<CovidCases> getCasesData() throws URISyntaxException;

    Future<VaccineCases> getVaccineData() throws URISyntaxException;

    Future<HistoryCases> getHistoryData() throws URISyntaxException;
}
