package com.ms.myapp.controller;


import com.ms.myapp.model.Country;
import com.ms.myapp.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping({"/", ""})
    public Flux<Country> showAll(){
        return countryService.getAll();
    }

    @GetMapping(value = "/{name}")
    public Mono<Country> findCountry(@PathVariable String name){
        return countryService.getCountryByName(name);
    }

    @GetMapping(value = "/ext/{name}")
    public Mono<Country> storeOne(@PathVariable String name){
        return countryService.getCountryFromExternal(name);

    }

    @GetMapping(value = "/ext/")
    public Flux<Country> storeAll(){
        return countryService.getAllCountiesFromExternal();

    }
}

