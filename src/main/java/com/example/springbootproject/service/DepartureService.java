package com.example.springbootproject.service;

import com.example.springbootproject.dto.DepartureDto;
import com.example.springbootproject.entity.Departure;
import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.exception.NotFoundException;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.mapper.DepartureMapper;
import com.example.springbootproject.repository.DepartureRepository;
import com.example.springbootproject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartureService {

    @Autowired
    Logger logger;
    @Autowired
    DepartureRepository departureRepository;
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartureMapper departureMapper;

    public String getMessageWithRandomNumber(){
        String message = "message with " + new Random().nextInt(10);
        logger.logMessage(message);
        return message;
    }

    public DepartureDto getByName(String name){

        Departure departureByName = departureRepository.findDepartureByName(name);
        if(Objects.isNull(departureByName)){
            throw new NotFoundException(Departure.class.getSimpleName(), "name", name);
        }

        List<Worker> workers = workerRepository.findAllByDepartureId(departureByName.getId());

        DepartureDto departureDto = departureMapper.toDto(departureByName)
                .setWorkerNames(workers.stream().map(Worker::getName).collect(Collectors.toList()));
        return departureDto;
    }

    public DepartureDto getDepartureById(int id){
        Optional<Departure> departure = departureRepository.findById(id);

        if(departure.isEmpty()){
            throw new NotFoundException(Departure.class.getSimpleName(), "id", id);
        }else{
            return departureMapper.toDto(departure.get());
        }
    }

    public Departure addDeparture(Departure departure){
        Optional<Departure> departureById = departureRepository.findById(departure.getId());

        if(departureById.isEmpty()) {
            return departureRepository.save(departure);
        }else{
            logger.logMessage(String.format("Departure with id = %s already exists", departure.getId()));
            return null;
        }
    }

    public Departure updateDeparture(Departure departure){
        Optional<Departure> departureById = departureRepository.findById(departure.getId());

        if(departureById.isEmpty()){
            logger.logMessage(String.format("No such departure to update with id = %s", departure.getId()));
            return null;
        }else {
            return departureRepository.save(departure);
        }
    }


    public List<Departure> getDepartures(int pageNumber, int size, String sortField){
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortField));
        Page<Departure> page = departureRepository.findAll(pageable);
        return page.getContent();
    }
}
