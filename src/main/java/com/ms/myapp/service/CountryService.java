package com.ms.myapp.service;

import com.ms.myapp.exception.MyNFException;
import com.ms.myapp.model.Country;
import com.ms.myapp.repository.CountryRepository;
import com.ms.myapp.service.external.ExternalCountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final ExternalCountryService externalCountryService;

    public void addCountry(Country country){
        countryRepository.addCountry(country.getName(),
                country.getCountry_code(),
                country.getCapital(),
                country.getPopulation(),
                country.getFlag_file_url())
                .subscribe(this::dispose, this::disposeError);
    }

    public void addAllCountries(Country country){
        countryRepository.save(country).subscribe(this::dispose);
    }

    public Flux<Country> getAll(){
        return countryRepository.getAllCountries();
    }

    public Mono<Country> getCountryByName(String name){
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        return countryRepository.findById(name)
                .switchIfEmpty(responseNotFound());

    }


    public Mono<Country> getCountryFromExternal(String name){
        Mono<Country> response = externalCountryService.getByName(name);
        response.subscribe(this::addCountry);
        return response;
    }

    public Flux<Country> getAllCountiesFromExternal(){
        Flux<Country> response = externalCountryService.getAll();
        response.subscribe(this::addCountry);
        return response;
    }


    private void dispose(Object c){

    }

    private Exception disposeError(Object c){
        return new Exception("Already Added!");
    }

    private <T> Mono <T> responseNotFound(){
        return Mono.error(new MyNFException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    private <T> Mono <T> responseInternalError(String msg){
        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, msg));
    }
}
