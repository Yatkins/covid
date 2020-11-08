package com.example.covid.controller;

import com.example.covid.service.CovidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CovidController {

    private final CovidService covidService;

    @GetMapping("/date/{year}/{month}/{day}")
    public String getQuarentineTime(@PathVariable int year,
                                    @PathVariable int month,
                                    @PathVariable int day){
        return covidService.getQuarentineTime(year, month, day);
    }

    @PostMapping("/date")
    public String getQuarantineTime(@RequestBody Date date){
        return covidService.getQuarentineTime(date.getYear(), date.getMonth(), date.getDay());
    }

}
