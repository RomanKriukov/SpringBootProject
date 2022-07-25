package com.example.springbootproject.service;

import com.example.springbootproject.dto.WorkerDto;
import com.example.springbootproject.entity.WorkerInfo;
import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.exception.NotFoundException;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.mapper.WorkerMapper;
import com.example.springbootproject.repository.WorkerInfoRepository;
import com.example.springbootproject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    Logger logger;
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    WorkerInfoRepository workerInfoRepository;

    @Autowired
    WorkerMapper workerMapper;

    public Worker addWorker(Worker worker){

        return workerRepository.save(worker);

//        Optional<Worker> workerById = workerRepository.findById(worker.getId());
//
//        if(workerById.isEmpty()){
//            return workerRepository.save(worker);
//        }else{
//            logger.logMessage(String.format("Worker with id = %s already exists", worker.getId()));
//            return null;
//        }
    }

    public List<Worker> getAllWorkers(){
        return workerRepository.findAll();
    }

    public Worker getWorkerById(int worker_id){
        Optional<Worker> workerById = workerRepository.findById(worker_id);

        if(workerById.isEmpty()){
            throw new NotFoundException(Worker.class.getSimpleName(), "id", worker_id);
        }else{
            return workerById.get();
        }
    }

    public List<WorkerDto> getWorkersByName(String name){
        List<Worker> workerByName = workerRepository.findWorkerByName(name);

        if(workerByName.isEmpty()){
            throw new NotFoundException(Worker.class.getSimpleName(), "name", name);
        }
        List<WorkerDto> workerDtoList = new ArrayList<>();
        for(Worker worker : workerByName){
            workerDtoList.add(workerMapper.toDto(worker));
        }
        return workerDtoList;
    }

    public List<Worker> getWorkersByDeparture(int departure_id){
        List<Worker> workersByDeparture = workerRepository.findAllByDepartureId(departure_id);

        return workersByDeparture;
    }

    public Worker updateWorker(Worker worker){
        Optional<Worker> workerById = workerRepository.findById(worker.getId());

        if(workerById.isEmpty()){
            logger.logMessage(String.format("No such worker to update with id = %s", worker.getId()));
            return null;
        }else {
            return workerRepository.save(worker);
        }
    }

    public WorkerInfo addWorkerInfo(WorkerInfo workerInfo){
        Optional<Worker> workerById = workerRepository.findById(workerInfo.getId());
        Optional<WorkerInfo> workerInfoById = workerInfoRepository.findById(workerInfo.getId());

        if(workerById.isEmpty()){
            logger.logMessage(String.format("Worker with id = %s is not exists", workerInfo.getId()));
            return null;
        }else{
            if(workerInfoById.isEmpty()){
                return workerInfoRepository.save(workerInfo);
            }else{
                logger.logMessage(String.format("Worker info with id = %s already exists", workerInfo.getId()));
                return null;
            }
        }
    }

    public WorkerInfo updateWorkerInfo(WorkerInfo workerInfo){
        Optional<Worker> workerById = workerRepository.findById(workerInfo.getId());
        Optional<WorkerInfo> workerInfoById = workerInfoRepository.findById(workerInfo.getId());

        if(workerById.isEmpty()){
            logger.logMessage(String.format("Worker with id = %s is not exists", workerInfo.getId()));
            return null;
        }else{
            if(workerInfoById.isEmpty()){
                logger.logMessage(String.format("Worker info with id = %s is not exists", workerInfo.getId()));
                return null;
            }else{
                return workerInfoRepository.save(workerInfo);
            }
        }
    }
}
