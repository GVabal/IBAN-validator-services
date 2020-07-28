package dev.vabalas.pojo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class IBANCountryTest {

    @Test
    void constructor() {
        IBANCountry ibanCountry = new IBANCountry("name", "SMB", "18",
                                                "\\w{18}", "TEST11112222333344", "no");
        Assertions.assertThat(ibanCountry.getName()).isInstanceOf(String.class);
        Assertions.assertThat(ibanCountry.getSymbol()).isInstanceOf(String.class);
        Assertions.assertThat(ibanCountry.getLength()).isInstanceOf(Integer.class);
        Assertions.assertThat(ibanCountry.getStructure()).isInstanceOf(String.class);
        Assertions.assertThat(ibanCountry.getExample()).isInstanceOf(String.class);
        Assertions.assertThat(ibanCountry.isSepa()).isInstanceOf(Boolean.class);
    }
}