package com.example.springbootproject.mapper;

import com.example.springbootproject.dto.DepartureDto;
import com.example.springbootproject.entity.Departure;
import org.springframework.stereotype.Component;

@Component
public class DepartureMapper {

    public DepartureDto toDto(Departure departure){
        DepartureDto departureDto = new DepartureDto()
                .setName(departure.getName())
                .setId(departure.getId())
                .setLocation(departure.getLocation());
        return departureDto;
    }

    public Departure toModel(DepartureDto dto){
        Departure departure = new Departure()
                .setId(dto.getId())
                .setName(dto.getName())
                .setLocation(dto.getLocation());
        return departure;
    }
}
