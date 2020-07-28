package dev.vabalas.pojo;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class IBANNumberDtoTest {

    @Test
    void constructor() {
        IBANNumberDto ibanNumberDto = new IBANNumberDto("TEST11112222", false);
        Assertions.assertThat(ibanNumberDto.getNumber()).isInstanceOf(String.class);
        Assertions.assertThat(ibanNumberDto.isValid()).isInstanceOf(Boolean.class);
    }
}