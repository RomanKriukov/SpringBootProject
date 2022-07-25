package com.example.springbootproject.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Accessors(chain = true)
public class Worker {

    @Id
    private int id;
    private String name;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "departure_id", nullable = false)
    private Departure departure;

    @OneToOne
    @JoinColumn(name = "id", nullable = true)
    private WorkerInfo workerInfo;
}
