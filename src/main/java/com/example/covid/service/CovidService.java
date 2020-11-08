package com.example.covid.service;

import com.example.covid.model.TimeServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CovidService{

    private final RestTemplate restTemplate;

    public String getQuarentineTime(int year, int month, int day){
        try {
            TimeServerResponse timeServerResponse = restTemplate.exchange("http://localhost:8080/api/v1/getTime", //before i had localhost:8382/api/v1/getTime
                    HttpMethod.GET,
                    null,
                    TimeServerResponse.class).getBody();

            assert timeServerResponse != null;

            LocalDate today = LocalDate.parse(timeServerResponse.getLocalTime());
            LocalDate covid_day = LocalDate.of(year, month, day);

            if(today.compareTo(covid_day) < 0){
                return "Error, that day is in the future...";
            }
            else{
                if(14 - ChronoUnit.DAYS.between(covid_day, today) > 0){
                    return "Quorenting for " + (14 - ChronoUnit.DAYS.between(covid_day, today)) + " more days.";
                }else{
                    return "Quorentine is over!!";
                }
            }
        }catch(RestClientException e){
            //nothing
        }return null;
    }
}
