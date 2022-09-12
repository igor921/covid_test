package org.computools.covid.input;

import com.neovisionaries.i18n.CountryCode;

//Interface for getting data. In this case country code
public interface Input {

    CountryCode getCode();
}
