package computools.covid.input;

import com.neovisionaries.i18n.CountryCode;
import org.computools.covid.input.Input;

public class TestInput implements Input {
    @Override
    public CountryCode getCode() {
        return CountryCode.FR;
    }
}
