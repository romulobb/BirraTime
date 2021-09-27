package com.santanderBirraTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.santanderBirraTime.model.weather.DTOWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.math.*;

import com.mashape.unirest.http.Unirest;

/**
 * Start up the Spring Boot Application
 */

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

   // @Autowired
  //  private static ObjectMapper objectMapper;

    //Usar WebClient de Spring
   /* @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }*/


    public static void main(String[] args) throws UnirestException {
        SpringApplication.run(Application.class, args);
        logger.info("the application is now running.....");

    }

}
