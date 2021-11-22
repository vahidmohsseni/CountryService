package com.ms.myapp.service;

import com.ms.myapp.model.CommonResponse;
import com.ms.myapp.model.Country;
import com.ms.myapp.repository.CoRepo;
import com.ms.myapp.repository.CountryRepository;
import com.ms.myapp.service.external.ExternalCountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final ExternalCountryService externalCountryService;
    private final CoRepo coRepo;

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
        Mono<Country> fallback = Mono.just(new CommonResponse("not found", 404));
        return countryRepository.findById(name).switchIfEmpty(fallback);

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
}
