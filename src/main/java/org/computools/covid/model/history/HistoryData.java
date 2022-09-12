package org.computools.covid.model.history;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
//representative of history data
public class HistoryData {

    private Map<String, Integer> dates;
}
