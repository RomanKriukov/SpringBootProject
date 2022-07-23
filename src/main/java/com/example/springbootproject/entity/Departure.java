package com.example.springbootproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
public class Departure {

    @Id
    private int id;
    private String name;
    private String location;
}
