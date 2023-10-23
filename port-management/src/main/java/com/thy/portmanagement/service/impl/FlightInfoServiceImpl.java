package com.thy.portmanagement.service.impl;

import com.thy.portmanagement.dto.FlightInfoDto;
import com.thy.portmanagement.dto.FlightInfoReq;
import com.thy.portmanagement.entity.FlightInfoEntity;
import com.thy.portmanagement.repository.FlightInfoRepository;
import com.thy.portmanagement.service.FlightInfoService;
import com.thy.portmanagement.service.GenerateRandomDataService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightInfoServiceImpl implements FlightInfoService {

    private final static int COUNT_OF_FLIGHT_INFO = 10;
    private final GenerateRandomDataService generateRandomDataService;
    private final FlightInfoRepository flightInfoRepository;

    public FlightInfoServiceImpl(GenerateRandomDataService generateRandomDataService,
                                 FlightInfoRepository flightInfoRepository) {
        this.generateRandomDataService = generateRandomDataService;
        this.flightInfoRepository = flightInfoRepository;
    }

    @PostConstruct
    public void fillFlightInfo() {
        generateRandomDataService.generateRandomFlightInfo(COUNT_OF_FLIGHT_INFO);
    }

    @Override
    public List<FlightInfoDto> getFlightInfo(FlightInfoReq req) {

        List<FlightInfoEntity> flightInfoEntities = flightInfoRepository.findByArrivalAirportAndDepartureAirport(req.to(), req.from());

        if (!CollectionUtils.isEmpty(flightInfoEntities)) {

            return flightInfoEntities.stream().map(FlightInfoDto::fromEntity).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public List<FlightInfoDto> getAllFlightInfo() {
        Iterator<FlightInfoEntity> flightInfoEntityIterator = flightInfoRepository.findAll().iterator();
        List<FlightInfoEntity> flightInfoEntities = new ArrayList<>();

        flightInfoEntityIterator.forEachRemaining(flightInfoEntities::add);

        if (!CollectionUtils.isEmpty(flightInfoEntities)) {

            return flightInfoEntities.stream().map(FlightInfoDto::fromEntity).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
