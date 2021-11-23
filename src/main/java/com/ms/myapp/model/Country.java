package com.ms.myapp.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    private void nameUnpack(Object name){
        if (name.getClass() == LinkedHashMap.class){
            setName(((Map<String, String>) name).getOrDefault("common", "NOT FOUND"));
        }
        else if (name.getClass() == String.class){
            setName((String) name);
        }
    }


    @JsonProperty("capital")
    private void capitalUnpack(Object cap){
        if(cap.getClass() == ArrayList.class){
            setCapital(((List<String>) cap).get(0));
        }
        else {
            setCapital((String) cap);
        }

    }

    @JsonProperty("flags")
    private void flagUnpack(Object flags){
        if (flags.getClass() == LinkedHashMap.class){
            setFlag_file_url(((Map<String, String>) flags).getOrDefault("png", "NOT FOUND"));
        }
        else {
            setFlag_file_url((String) flags);
        }
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
