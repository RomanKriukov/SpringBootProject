package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Integer> {

}
