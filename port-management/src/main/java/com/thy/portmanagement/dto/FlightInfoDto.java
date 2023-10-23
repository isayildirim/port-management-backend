package com.thy.portmanagement.dto;

import com.thy.portmanagement.entity.FlightInfoEntity;

public record FlightInfoDto(
        String departureAirport,
        String arrivalAirport,
        String departureTime,
        String arrivalTime,
        int baggageAllowance,
        String price,
        boolean hasStop
) {
    public static FlightInfoDto fromEntity(FlightInfoEntity flightInfo) {
        return new FlightInfoDto(
                flightInfo.getDepartureAirport(),
                flightInfo.getArrivalAirport(),
                flightInfo.getDepartureTime(),
                flightInfo.getArrivalTime(),
                flightInfo.getBaggageAllowance(),
                flightInfo.getPrice(),
                flightInfo.isHasStop());
    }
}
