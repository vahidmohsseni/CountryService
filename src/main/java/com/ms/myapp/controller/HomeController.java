package com.ms.myapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class HomeController {

    private static String indexPage = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n" + "\n" + "<h1>Welcome!</h1>\n" + "\n" + "<p>Go to <a href='/countries/'>/countries/</a> page.</p>\n" + "<p>For More information refer <a target=_blank href='https://github.com/vahidmohsseni/CountryService'>here</a>!</p>" + "\n" + "</body>\n" + "</html>";


    @GetMapping(value = {"", "/"})
    private String homePage(){
        return indexPage;
    }


    @GetMapping(value = {"/nordea"})
    private String norPage(){
        return "Hello Nordea!";
    }


}
