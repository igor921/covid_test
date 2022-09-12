package org.computools.covid.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CovidInformationDto {
//representation output data
    private String country;
    private int confirmed;
    private int newConfirmed;
    private int recovered;
    private int deaths;
    private double vaccinatedLevel;

    //for print
    @Override
    public String toString() {
        return country +
                "\n confirmed = " + confirmed +
                "\n newConfirmed = " + newConfirmed +
                "\n recovered = " + recovered +
                "\n deaths = " + deaths +
                "\n vaccinatedLevel % = " + String.format("%1$,.2f", vaccinatedLevel);
    }
}
