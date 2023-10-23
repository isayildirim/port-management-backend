package com.thy.portmanagement.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thy.portmanagement.dto.AirportInfoDto;
import com.thy.portmanagement.entity.AirportEntity;
import com.thy.portmanagement.entity.FlightInfoEntity;
import com.thy.portmanagement.exception.GeneralExceptionResponse;
import com.thy.portmanagement.repository.AirportRepository;
import com.thy.portmanagement.repository.FlightInfoRepository;
import com.thy.portmanagement.service.GenerateRandomDataService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

@Service
public class GenerateRandomDataServiceImpl implements GenerateRandomDataService {

    private static final String[] AIRPORTS = {"IST", "SAW", "KCO", "ESB", "BJV", "JFK", "ASR", "ADA", "AYT"};
    private static final String[] TIMES = {"08:00", "10:30", "14:45", "18:15", "22:30"};
    private static final int BAGGAGE_ALLOWANCE = 15;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private static final String AIRPORT_CONSTANTS = "static/airports.json";

    private final FlightInfoRepository flightInfoRepository;
    private final AirportRepository airportRepository;

    public GenerateRandomDataServiceImpl(FlightInfoRepository flightInfoRepository,
                                         AirportRepository airportRepository) {
        this.flightInfoRepository = flightInfoRepository;
        this.airportRepository = airportRepository;
    }

    @Transactional
    public void generateRandomFlightInfo(int count) {
        for (int i = 0; i < count; i++) {
            FlightInfoEntity flightInfo = new FlightInfoEntity();

            flightInfo.setDepartureAirport(getRandomAirport());
            flightInfo.setArrivalAirport(getRandomAirport());
            flightInfo.setDepartureTime(getRandomTime());
            flightInfo.setArrivalTime(getRandomTime());
            flightInfo.setBaggageAllowance(BAGGAGE_ALLOWANCE);
            flightInfo.setPrice(decimalFormat.format(getRandomPrice()));

            flightInfoRepository.save(flightInfo);
        }
    }

    private String getRandomAirport() {
        Random random = new Random();
        int index = random.nextInt(AIRPORTS.length);
        return AIRPORTS[index];
    }

    private String getRandomTime() {
        Random random = new Random();
        int index = random.nextInt(TIMES.length);
        return TIMES[index];
    }

    private double getRandomPrice() {
        Random random = new Random();
        return 100 + (1000 - 100) * random.nextDouble();
    }

    @Transactional
    public void saveConstantAirports() {

        List<AirportInfoDto> airports = readJsonToAirportEntities(AIRPORT_CONSTANTS);

        if (!CollectionUtils.isEmpty(airports)) {
            for (AirportInfoDto airport : airports) {

                if (!isExistAirport(airport.code())) {

                    AirportEntity airportEntity = new AirportEntity(airport.city(), airport.code(), airport.country(), airport.name());
                    airportRepository.save(airportEntity);
                }
            }
        }
    }

    private boolean isExistAirport(String code) {

        return airportRepository.findByCode(code).isPresent();
    }

    public List<AirportInfoDto> readJsonToAirportEntities(String jsonFilePath) {
        try {
            ClassPathResource resource = new ClassPathResource(jsonFilePath);
            InputStream inputStream = resource.getInputStream();
            byte[] fileContent = FileCopyUtils.copyToByteArray(inputStream);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(fileContent, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new GeneralExceptionResponse(String.format("An error occurred while reading the file: %s", e.getMessage()));
        }
    }

}
