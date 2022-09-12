package org.computools.covid.service;

import com.google.gson.Gson;
import com.neovisionaries.i18n.CountryCode;
import org.apache.http.client.utils.URIBuilder;
import org.computools.covid.model.cases.CovidCases;
import org.computools.covid.model.history.HistoryCases;
import org.computools.covid.model.vaccine.VaccineCases;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.Future;

//Using covid-api.mmediagroup.fr
public class ApiClient implements ApiClientInterface{

    //API URL and constants
    private final String BASE_URL = "https://covid-api.mmediagroup.fr/v1/";
    private final String COVID_CASES_PATH = "cases";
    private final String VACCINES_CASES_PATH = "vaccines";
    private final String HISTORY_CASES_PATH = "history";
    private final String COUNTRY_PARAM = "country";

    //Uses for serialize/deserialize data
    private final Gson gson;
    //Country code work with
    private final CountryCode countryCode;
    //Http client
    private HttpClient httpClient;

    public ApiClient(CountryCode countryCode) {
        this.countryCode = countryCode;
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    //Get base data
    @Override
    public Future<CovidCases> getCasesData() throws URISyntaxException {
        return doRequest(COVID_CASES_PATH, Map.of(), CovidCases.class);
    }

    //Get data about vaccined people
    @Override
    public Future<VaccineCases> getVaccineData() throws URISyntaxException {
        return doRequest(VACCINES_CASES_PATH, Map.of(), VaccineCases.class);
    }

    //Get history data, cases by day
    @Override
    public Future<HistoryCases> getHistoryData() throws URISyntaxException {
        return doRequest(HISTORY_CASES_PATH, Map.of("status", "confirmed"), HistoryCases.class);
    }

    //method for request processing
    private Future doRequest(String path, Map<String, String> params, Class type) throws URISyntaxException {
        return httpClient.sendAsync(HttpRequest.newBuilder().uri(buildUri(path, params)).GET().build() , HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> gson.fromJson(body, type));
    }

    //build request params
    private URI buildUri(String path, Map<String, String> params) throws URISyntaxException {

        var builder = new URIBuilder(BASE_URL + path);

        builder.addParameter(COUNTRY_PARAM, countryCode.getName());

        params.forEach(builder::addParameter);

        return builder.build();

    }
}
