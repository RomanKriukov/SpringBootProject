package com.example.springbootproject.service;

import com.example.springbootproject.entity.Departure;
import com.example.springbootproject.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DepartureService {

    List<Departure> departures = new ArrayList<>();

    @Autowired
    Logger logger;

    public String getMessageWithRandomNumber(){
        String message = "message with " + new Random().nextInt(10);
        logger.logMessage(message);
        return message;
    }

    public Departure addDeparture(Departure departure){
        if(departures.stream().noneMatch(d -> d.getId() == departure.getId())){
            departures.add(departure);
        }
        return departures.stream().filter(d -> d.getId() == departure.getId()).findFirst().orElse(null);
    }

    public List<Departure> getDepartures(){
        return departures;
    }
}
