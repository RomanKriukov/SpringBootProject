package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    @Query("select w from Worker as w where w.departure.id = :departure_id")
    List<Worker> findAllByDepartureId(int departure_id);
}
