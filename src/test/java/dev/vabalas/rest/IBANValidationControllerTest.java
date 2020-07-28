package dev.vabalas.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IBANValidationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void validateMany() {

        ResponseEntity<List> response = restTemplate.exchange("/rest",
                HttpMethod.POST,
                new HttpEntity("{\"numbers\":[\"GL8964710123456700\",\"GL89647101234\",\"GL8964710123456789\"]}"),
                List.class);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(response.getBody().size()).isEqualTo(3);
        String item1 = response.getBody().get(0).toString();
        Assertions.assertThat(item1.contains("false")).isEqualTo(true);
        String item2 = response.getBody().get(1).toString();
        Assertions.assertThat(item2.contains("false")).isEqualTo(true);
        String item3 = response.getBody().get(2).toString();
        Assertions.assertThat(item3.contains("true")).isEqualTo(true);
    }
}