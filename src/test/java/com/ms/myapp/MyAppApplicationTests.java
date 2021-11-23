package com.ms.myapp;

import com.ms.myapp.model.CommonResponse;
import com.ms.myapp.model.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyAppApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("External API Test")
    void getCountryExt() {
        Country country = new Country();
        country.setName("Finland");
        country.setCountry_code("FI");
        country.setCapital("Helsinki");
        country.setPopulation(5530719);
        country.setFlag_file_url("https://flagcdn.com/w320/fi.png");
        webClient.get().uri("/countries/ext/{name}", "Finland")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Country.class)
                .toString().equals(country.toString());
    }

    @Test
    @DisplayName("External API Test")
    void getCountriesFromExternal(){
        webClient.get().uri("/countries/ext/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Country.class);
    }

    @Test
    @DisplayName("Internal all countries' API Test")
    void getCountries(){
        webClient.get().uri("/countries/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Country.class);
    }

    @Test
    @DisplayName("Internal Country by name API Test")
    void getCountry(){
        Country country = new Country();
        country.setName("Finland");
        country.setCountry_code("FI");
        country.setCapital("Helsinki");
        country.setPopulation(5530719);
        country.setFlag_file_url("https://flagcdn.com/w320/fi.png");
        webClient.get().uri("/countries/{name}", "Finland")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Country.class)
                .toString().equals(country.toString());
    }

    @Test
    @DisplayName("API 404 test")
    void nFTest(){
        webClient.get().uri("/countries/{name}", "NotFoundTest")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isNotFound();

    }

    @Test
    @DisplayName("External API 404 test")
    void externalNFTest(){
        webClient.get().uri("/countries/ext/{name}", "NotFoundTest")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isNotFound();

    }


}
