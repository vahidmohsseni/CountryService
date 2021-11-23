package com.ms.myapp;

import com.ms.myapp.model.Country;
import com.ms.myapp.repository.CountryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryTest {

    @Test
    @DisplayName("Add item to Reo test")
    void testRepositoryAdd (){
        Country country = new Country();
        country.setName("Finland");
        country.setCountry_code("FI");
        country.setCapital("Helsinki");
        country.setPopulation(5530719);
        country.setFlag_file_url("https://flagcdn.com/w320/fi.png");

        CountryRepository countryRepository = Mockito.mock(CountryRepository.class);
        countryRepository
                .addCountry(
                        country.getName(),
                        country.getCountry_code(),
                        country.getCapital(),
                        country.getPopulation(),
                        country.getFlag_file_url());

        Mockito.verify(countryRepository,
                        times(1))
                .addCountry(
                        "Finland",
                        "FI", "Helsinki",
                        5530719, "https://flagcdn.com/w320/fi.png");

    }


}