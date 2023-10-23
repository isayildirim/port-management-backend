package com.thy.portmanagement.service.impl;

import com.thy.portmanagement.dto.AirportInfoDto;
import com.thy.portmanagement.entity.AirportEntity;
import com.thy.portmanagement.repository.AirportRepository;
import com.thy.portmanagement.service.AirportInfoService;
import com.thy.portmanagement.service.GenerateRandomDataService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportInfoServiceImpl implements AirportInfoService {

    private final GenerateRandomDataService generateRandomDataService;
    private final AirportRepository airportRepository;

    public AirportInfoServiceImpl(GenerateRandomDataService generateRandomDataService,
                                  AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
        this.generateRandomDataService = generateRandomDataService;
    }

    @PostConstruct
    public void fillAirportInfo() {
        generateRandomDataService.saveConstantAirports();
    }

    @Override
    public List<AirportInfoDto> getAirportInfo(String airportValue) {

        List<AirportEntity> airportEntity = airportRepository.findByCityContainingOrCodeContaining(airportValue, airportValue);

        if (!CollectionUtils.isEmpty(airportEntity)) {

            return airportEntity.stream().map(AirportInfoDto::fromEntity).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public List<AirportInfoDto> getAllAirportInfo() {
        Iterator<AirportEntity> airportEntityIterator = airportRepository.findAll().iterator();
        List<AirportEntity> airportEntities = new ArrayList<>();

        airportEntityIterator.forEachRemaining(airportEntities::add);

        if (!CollectionUtils.isEmpty(airportEntities)) {

            return airportEntities.stream().map(AirportInfoDto::fromEntity).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
