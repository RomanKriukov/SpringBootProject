package com.example.springbootproject.controller;

import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    WorkerRepository repository;

    @PostMapping("/worker")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker){
        return ResponseEntity.ok(repository.save(worker));
    }

    @GetMapping("/workers")
    public List<Worker> getAllWorkers(){
        return repository.findAll();
    }
}
