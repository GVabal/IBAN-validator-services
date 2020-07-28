package dev.vabalas.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class IBANCountryRepositoryTest {

    @Autowired
    private IBANCountryRepository repository;

    @Test
    void initData() {
        Assertions.assertThat(repository.getCountries()).isNotEmpty();
    }

}