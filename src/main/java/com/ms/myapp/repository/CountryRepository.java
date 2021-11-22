package com.ms.myapp.repository;

import com.ms.myapp.model.Country;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CountryRepository extends ReactiveCrudRepository<Country, String> {

    @Query("INSERT INTO country (name, country_code, capital, population, flag_file_url)" +
            " VALUES (:name, :country_code, :capital, :population, :flag_file_url);")
    Mono<Country> addCountry(String name,
                             String country_code,
                             String capital,
                             int population,
                             String flag_file_url);



    @Query("SELECT country.name, country.country_code FROM country")
    Flux<Country> getAllCountries();
}
