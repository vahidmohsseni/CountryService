package com.ms.myapp.repository;


import com.ms.myapp.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;

@RequiredArgsConstructor
@Component
public class CoRepo {
    private final R2dbcEntityTemplate template;

    public Disposable addCountry(Country country){
        return this.template.insert(Country.class)
                .using(country).subscribe(this::Dis);
    }


    public void Dis(Country country) {
        System.out.println("faghat jahate kir shodan");
    }
}
