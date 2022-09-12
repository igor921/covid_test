package org.computools.covid;

import org.computools.covid.input.ConsoleInput;

import org.computools.covid.output.ConsoleOutput;
import org.computools.covid.service.ApiClient;
import org.computools.covid.service.ApiClientInterface;
import org.computools.covid.service.DataProcessor;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class CovidApplication {

    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException {
        ApiClientInterface apiClientInterface = new ApiClient(new ConsoleInput().getCode());
        var covidInformationDto = new DataProcessor(apiClientInterface).process();
        new ConsoleOutput().print(covidInformationDto);
    }

}
