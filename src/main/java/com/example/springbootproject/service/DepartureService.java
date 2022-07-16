package com.example.springbootproject.service;

import com.example.springbootproject.entity.Departure;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.repository.DepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartureService {

    @Autowired
    Logger logger;

    @Autowired
    DepartureRepository repository;

    public String getMessageWithRandomNumber(){
        String message = "message with " + new Random().nextInt(10);
        logger.logMessage(message);
        return message;
    }

    public Departure addDeparture(Departure departure){
        Optional<Departure> departureById = repository.findById(departure.getId());

        if(departureById.isEmpty()) {
            return repository.save(departure);
        }else{
            logger.logMessage(String.format("Departure with id = %s already exists", departure.getId()));
            return null;
        }
    }

    public Departure updateDeparture(Departure departure){
        Optional<Departure> departureById = repository.findById(departure.getId());

        if(departureById.isEmpty()){
            logger.logMessage(String.format("No such departure to update with id = %s", departure.getId()));
            return null;
        }else {
            return repository.save(departure);
        }
    }

    public List<Departure> getDepartures(){
        return repository.findAll();
    }
}
