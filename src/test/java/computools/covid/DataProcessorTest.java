package computools.covid;

import org.computools.covid.output.ConsoleOutput;
import org.computools.covid.service.DataProcessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import computools.covid.service.ApiClient;
import org.junit.jupiter.api.TestInstance;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataProcessorTest {

    private DataProcessor dataProcessor;

    @BeforeAll
    void init() {
        dataProcessor = new DataProcessor(new ApiClient());
    }

    @Test
    void testCasesData() throws URISyntaxException, ExecutionException, InterruptedException {
        var result = dataProcessor.process();

        new ConsoleOutput().print(result);

        assertEquals(result.getConfirmed(), 100);
        assertEquals(result.getRecovered(), 50);
        assertEquals(result.getDeaths(), 50);
        assertEquals(result.getNewConfirmed(), 5);
        assertEquals(result.getVaccinatedLevel(), 20.);
    }
}
