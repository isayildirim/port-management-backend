package com.thy.portmanagement.service.impl;

import com.thy.portmanagement.dto.AirportInfoDto;
import com.thy.portmanagement.entity.AirportEntity;
import com.thy.portmanagement.repository.AirportRepository;
import com.thy.portmanagement.service.GenerateRandomDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportInfoServiceImplTest {

    private AirportInfoServiceImpl airportInfoService;
    private AirportRepository airportRepository;

    private GenerateRandomDataService generateRandomDataService;

    @BeforeEach
    public void setUp() {
        airportRepository = Mockito.mock(AirportRepository.class);
        generateRandomDataService = Mockito.mock(GenerateRandomDataService.class);

        airportInfoService = new AirportInfoServiceImpl(generateRandomDataService, airportRepository);
    }

    @Test
    public void whenAirportInfoCalledWithValidRequest_itShouldReturnValidAirportInfoDto() {

        List<AirportEntity> mockAirports = new ArrayList<>();
        mockAirports.add(new AirportEntity("Istanbul", "IST", "Turkey", "Ataturk"));

        Mockito.when(airportRepository.findByCityContainingOrCodeContaining("Istanbul", "Istanbul")).thenReturn(mockAirports);

        List<AirportInfoDto> result = airportInfoService.getAirportInfo("Istanbul");

        assertEquals(mockAirports.size(), result.size());
    }
}