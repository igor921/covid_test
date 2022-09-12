package org.computools.covid.model.cases;

import lombok.*;

@Getter
@Setter
@Builder
//representative of covid data
public class CovidCasesData {

    private String country;
    private int confirmed;
    private int recovered;
    private int deaths;
}
