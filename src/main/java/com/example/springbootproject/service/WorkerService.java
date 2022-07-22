package com.example.springbootproject.service;

import com.example.springbootproject.entity.WorkerInfo;
import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.repository.WorkerInfoRepository;
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

    @Autowired
    WorkerInfoRepository workerInfoRepository;

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
