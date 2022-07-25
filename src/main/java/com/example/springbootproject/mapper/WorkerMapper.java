package com.example.springbootproject.mapper;

import com.example.springbootproject.dto.WorkerDto;
import com.example.springbootproject.entity.Worker;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper {

    public WorkerDto toDto(Worker worker){
        return new WorkerDto()
                .setId(worker.getId())
                .setName(worker.getName())
                .setSalary(worker.getSalary());
    }

    public Worker toModel(WorkerDto workerDto){
        return new Worker()
                .setId(workerDto.getId())
                .setName(workerDto.getName())
                .setSalary(workerDto.getSalary());
    }
}
