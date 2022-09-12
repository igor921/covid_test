package org.computools.covid.input;

import com.neovisionaries.i18n.CountryCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//Implementation for getting country code from console
public class ConsoleInput implements Input {

    //command for info
    private final String HELP_COMMAND = "help";

    //override receiving of code
    @Override
    public CountryCode getCode() {
        CountryCode countryCode = null;

        //create console reader with auto close
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                //Info message
                System.out.printf("Enter country code (type '%s' to see possible values): ", HELP_COMMAND);
                //read inout
                String country = reader.readLine();
                //check action: display info or parse value
                if(HELP_COMMAND.equals(country)) {
                    printPossibleValues();
                } else countryCode = CountryCode.getByAlpha2Code(country);
              // while value will be correct
            } while (countryCode == null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryCode;
    }

    private void printPossibleValues() {
        Arrays.asList(CountryCode.values()).forEach(countryCode ->
                System.out.printf("%s - %s%n",countryCode.getAlpha2(), countryCode.getName()));
    }
}
