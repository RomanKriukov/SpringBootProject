package com.example.springbootproject.controller;

import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.entity.WorkerInfo;
import com.example.springbootproject.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping("/worker")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker){
        Worker returnValue = workerService.addWorker(worker);

        if(Objects.isNull(returnValue)){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(returnValue);
        }
    }

    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> getAllWorkers(){
        List<Worker> workers = workerService.getAllWorkers();

        if(workers.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(workers);
        }
    }

    @GetMapping("/worker/{worker_id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable int worker_id){
        Worker workerById = workerService.getWorkerById(worker_id);

        if(Objects.isNull(workerById)){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(workerById);
        }
    }

    @GetMapping("/workers/{name}")
    public ResponseEntity<List<Worker>> getWorkerByName(@PathVariable String name){
        List<Worker> workerByName = workerService.getWorkerByName(name);

        if(workerByName.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(workerByName);
        }
    }

    @GetMapping("/departure/{departure_id}/workers")
    public ResponseEntity<List<Worker>> getWorkersByDeparture(@PathVariable int departure_id){
        List<Worker> workersByDeparture = workerService.getWorkersByDeparture(departure_id);

        if(workersByDeparture.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(workersByDeparture);
        }
    }

    @PutMapping("/worker/{id}")
    public ResponseEntity<Worker> updateWorker(@RequestBody Worker worker, @PathVariable int id){
        worker.setId(id);
        Worker returnValue = workerService.updateWorker(worker);

        if(Objects.isNull(returnValue)){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(returnValue);
        }
    }

    @PostMapping("/worker/{id}/info")
    public ResponseEntity<WorkerInfo> addWorkerInfo(@PathVariable int id, @RequestBody WorkerInfo workerInfo){
        workerInfo.setId(id);
        WorkerInfo returnValue = workerService.addWorkerInfo(workerInfo);

        if(Objects.isNull(returnValue)){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(returnValue);
        }
    }

    @PutMapping("/worker/{id}/info")
    public ResponseEntity<WorkerInfo> updateWorkerInfo(@PathVariable int id, @RequestBody WorkerInfo workerInfo){
        workerInfo.setId(id);
        WorkerInfo returnValue = workerService.updateWorkerInfo(workerInfo);

        if(Objects.isNull(returnValue)){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(workerInfo);
        }
    }
}
