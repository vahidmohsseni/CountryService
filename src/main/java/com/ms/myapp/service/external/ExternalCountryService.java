package com.ms.myapp.service.external;

import com.ms.myapp.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
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
        Mono<Country> response = webClient.get()
                .uri("/name/{name}", name)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(res -> {
                    if (res.statusCode().equals(HttpStatus.OK)){
                        return res.bodyToFlux(Country.class).next();
                    }else if (res.statusCode().is4xxClientError()){
                        return responseNotFound();
                    }
                    return responseInternalError("External Service Down");
                });
        return response;

    }

    public Flux<Country> getAll(){
        return webClient.get()
                .uri("/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Country.class);

    }

    private <T> Mono <T> responseNotFound(){
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found"));
    }

    private <T> Mono <T> responseInternalError(String msg){
        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, msg));
    }

}
