package org.computools.covid.service;

import org.computools.covid.dto.CovidInformationDto;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DataProcessor {

    private ApiClientInterface apiClient;

    public DataProcessor(ApiClientInterface apiClient) {
        this.apiClient = apiClient;
    }

    public CovidInformationDto process() throws URISyntaxException, ExecutionException, InterruptedException {

        //Get data from client async
        var historyCasesFuture = apiClient.getHistoryData();
        var covidCasesFuture = apiClient.getCasesData();
        var vaccineCasesFuture = apiClient.getVaccineData();

        //get data from request
        var historyData = historyCasesFuture.get().getData();
        var covidCasesData = covidCasesFuture.get().getData();
        var vaccineData = vaccineCasesFuture.get().getData();

        //build DTO and calculate data
         var builder = CovidInformationDto.builder()
                .confirmed(covidCasesData.getConfirmed())
                .recovered(covidCasesData.getRecovered())
                .country(covidCasesData.getCountry())
                .newConfirmed(newConfirmed(historyData.getDates()))
                .deaths(covidCasesData.getDeaths());

         if(vaccineData != null) {
                builder.vaccinatedLevel(100. * vaccineData.getPeopleVaccinated() / vaccineData.getPopulation());
         }

         return builder.build();
    }

    //calculate new confirmed
    private int newConfirmed(Map<String, Integer> data) {
        //no data
        if (data.isEmpty())
            return 0;

        //if first date
        if(data.size() == 1)
            return data.values().stream().findFirst().get();

        //get diff between 2 latest
        ArrayList<Integer> casesList = new ArrayList<>(data.values());

        return casesList.get(0) - casesList.get(1);
    }


}
