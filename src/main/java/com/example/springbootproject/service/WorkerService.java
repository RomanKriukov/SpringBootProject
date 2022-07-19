package com.example.springbootproject.service;

import com.example.springbootproject.entity.Departure;
import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    Logger logger;
    @Autowired
    WorkerRepository workerRepository;

    public Worker addWorker(Worker worker){
        Optional<Worker> workerById = workerRepository.findById(worker.getId());

        if(workerById.isEmpty()){
            return workerRepository.save(worker);
        }else{
            logger.logMessage(String.format("Worker with id = %s already exists", worker.getId()));
            return null;
        }
    }

    public List<Worker> getAllWorkers(){
        return workerRepository.findAll();
    }

    public Worker getWorkerById(int worker_id){
        Optional<Worker> workerById = workerRepository.findById(worker_id);

        if(workerById.isEmpty()){
            return null;
        }else{
            return workerById.get();
        }
    }

    public List<Worker> getWorkerByName(String name){
        List<Worker> workerByName = workerRepository.findWorkerByName(name);

        return workerByName;
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
}
