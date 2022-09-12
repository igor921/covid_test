package org.computools.covid.model.vaccine;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//representative of vaccined data
public class VaccineData {

    private int population;

    @SerializedName("people_vaccinated")
    private int peopleVaccinated;
}
