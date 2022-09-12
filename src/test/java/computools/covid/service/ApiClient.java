package computools.covid.service;

import com.neovisionaries.i18n.CountryCode;
import org.computools.covid.model.cases.CovidCases;
import org.computools.covid.model.cases.CovidCasesData;
import org.computools.covid.model.history.HistoryCases;
import org.computools.covid.model.history.HistoryData;
import org.computools.covid.model.vaccine.VaccineCases;
import org.computools.covid.model.vaccine.VaccineData;
import org.computools.covid.service.ApiClientInterface;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ApiClient implements ApiClientInterface {
    @Override
    public Future<CovidCases> getCasesData() {
        CovidCases covidCases = new CovidCases();
        covidCases.setData(CovidCasesData.builder()
                        .confirmed(100)
                        .deaths(50)
                        .recovered(50)
                        .country(CountryCode.FR.getName())
                .build());
        return CompletableFuture.completedFuture(covidCases);
    }

    @Override
    public Future<VaccineCases> getVaccineData() {
        VaccineCases vaccineCases = new VaccineCases();
        vaccineCases.setData(VaccineData.builder()
                        .peopleVaccinated(200000)
                        .population(1000000)
                .build());
        return CompletableFuture.completedFuture(vaccineCases);
    }

    @Override
    public Future<HistoryCases> getHistoryData() {
        Map<String, Integer> dates = Map.of("2022-09-11", 80, "2022-09-12", 85);
        HistoryCases historyCases = new HistoryCases();
        historyCases.setData(HistoryData.builder()
                .dates(dates)
                .build());
        return CompletableFuture.completedFuture(historyCases);
    }
}
