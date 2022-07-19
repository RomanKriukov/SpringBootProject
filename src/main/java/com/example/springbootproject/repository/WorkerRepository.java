package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    @Query("select w from Worker as w where w.departure.id = :departure_id")
    List<Worker> findAllByDepartureId(@Param("departure_id") int departure_id);

    @Query("select w from Worker as w where w.name = :name")
    List<Worker> findWorkerByName(@Param("name") String name);
}
