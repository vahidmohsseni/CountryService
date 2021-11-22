package com.ms.myapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class Country extends BaseModel{

    public Country(){}

    public Country(String name, String country_code){
        this.name = name;
        this.country_code = country_code;
    }

    public Country(String name, String country_code, String capital, int population, String flag_file_url){
        this.name = name;
        this.country_code = country_code;
        this.capital = capital;
        this.population = population;
        this.flag_file_url = flag_file_url;
    }


    @Id
    private String name;

    private String country_code;

    private String capital;

    private int population;

    private String flag_file_url;


    @JsonProperty("name")
    private void nameUnpack(Map<String, Object> name){
        setName((String) name.getOrDefault("common", "NOT FOUND"));
    }

    @JsonProperty("capital")
    private void capitalUnpack(List<String> cap){
        setCapital(cap.get(0));

    }

    @JsonProperty("flags")
    private void flagUnpack(Map<String, Object> flags){
        setFlag_file_url((String) flags.getOrDefault("png", "NOT FOUND"));
    }

    @JsonProperty("cca2")
    private void setCC(String cc){
        setCountry_code(cc);
    }

    @JsonProperty("population")
    private void setPop(int population){
        setPopulation(population);
    }

}
