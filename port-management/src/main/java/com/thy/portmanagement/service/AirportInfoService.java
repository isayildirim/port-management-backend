package com.thy.portmanagement.service;

import com.thy.portmanagement.dto.AirportInfoDto;

import java.util.List;

public interface AirportInfoService {

    List<AirportInfoDto> getAirportInfo(String airportValue);

    List<AirportInfoDto> getAllAirportInfo();
}
