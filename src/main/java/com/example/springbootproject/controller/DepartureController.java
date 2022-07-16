package com.example.springbootproject.controller;

import com.example.springbootproject.entity.Departure;
import com.example.springbootproject.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartureController {

    @Autowired
    DepartureService departureService;

    @GetMapping("/hello")
    public String hello(){
        return departureService.getMessageWithRandomNumber();
    }

    @PostMapping("/departure")
    public Departure addDeparture(@RequestBody Departure departure){
        return departureService.addDeparture(departure);
    }

    @GetMapping("/departures")
    public List<Departure> getAllDepartures(){
        return departureService.getDepartures();
    }

    @PutMapping("/departure/{id}")
    public Departure updateDeparture(@RequestBody Departure departure, @PathVariable int id){
        departure.setId(id);
        return departureService.updateDeparture(departure);
    }
}
