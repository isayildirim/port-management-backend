package com.thy.portmanagement.service.impl;

import com.thy.portmanagement.dto.FlightInfoDto;
import com.thy.portmanagement.dto.FlightInfoReq;
import com.thy.portmanagement.entity.FlightInfoEntity;
import com.thy.portmanagement.repository.FlightInfoRepository;
import com.thy.portmanagement.service.GenerateRandomDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightInfoServiceImplTest {

    private FlightInfoServiceImpl flightInfoService;
    private FlightInfoRepository flightInfoRepository;

    private GenerateRandomDataService generateRandomDataService;

    @BeforeEach
    public void setUp() {
        flightInfoRepository = Mockito.mock(FlightInfoRepository.class);
        generateRandomDataService = Mockito.mock(GenerateRandomDataService.class);

        flightInfoService = new FlightInfoServiceImpl(generateRandomDataService, flightInfoRepository);
    }

    @Test
    public void whenFlightInfoCalledWithValidRequest_itShouldReturnValidFlightInfoDto() {

        List<FlightInfoEntity> mockFlightInfoEntities = new ArrayList<>();
        mockFlightInfoEntities.add(createFlightInfoEntity());
        FlightInfoReq request = new FlightInfoReq("Ankara", "Istanbul");

        Mockito.when(flightInfoRepository.findByArrivalAirportAndDepartureAirport(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(mockFlightInfoEntities);

        List<FlightInfoDto> result = flightInfoService.getFlightInfo(request);

        assertEquals(mockFlightInfoEntities.size(), result.size());
    }

    private FlightInfoEntity createFlightInfoEntity() {

        FlightInfoEntity flightInfo = new FlightInfoEntity();
        flightInfo.setDepartureAirport("Ankara");
        flightInfo.setArrivalAirport("Istanbul");
        flightInfo.setDepartureTime("2023-12-01 08:00");
        flightInfo.setArrivalTime("2023-12-01 09:30");
        flightInfo.setBaggageAllowance(20);
        flightInfo.setPrice("250.00");
        flightInfo.setHasStop(false);

        return flightInfo;
    }
}