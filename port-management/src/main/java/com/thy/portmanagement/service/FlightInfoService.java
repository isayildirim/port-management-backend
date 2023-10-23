package com.thy.portmanagement.service;

import com.thy.portmanagement.dto.FlightInfoDto;
import com.thy.portmanagement.dto.FlightInfoReq;

import java.util.List;

public interface FlightInfoService {
    List<FlightInfoDto> getFlightInfo(FlightInfoReq req);

    List<FlightInfoDto> getAllFlightInfo();
}
