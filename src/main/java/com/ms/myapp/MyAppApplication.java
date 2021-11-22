package com.ms.myapp;


import io.r2dbc.spi.ConnectionFactory;
import lombok.var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;


@SpringBootApplication
public class MyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }


    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        var initializer = new ConnectionFactoryInitializer();

        initializer.setConnectionFactory(connectionFactory);


        return initializer;
    }

}
