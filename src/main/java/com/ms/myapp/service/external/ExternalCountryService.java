package com.ms.myapp.service.external;

import com.ms.myapp.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalCountryService {

    private final WebClient webClient;
    private static final String TYPE = "application/json";
    private static final String BASE_URL = "https://restcountries.com/v3.1/";


    public ExternalCountryService(){
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, TYPE)
                .build();
    }

    public Mono<Country> getByName(String name){
        return webClient.get()
                .uri("/name/{name}", name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Country.class).next();

    }

    public Flux<Country> getAll(){
        return webClient.get()
                .uri("/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Country.class);

    }

}
