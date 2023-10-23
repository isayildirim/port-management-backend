package com.thy.portmanagement.dto;

import com.thy.portmanagement.entity.AirportEntity;

public record AirportInfoDto(
        String city,
        String country,
        String name,
        String code
) {
    public static AirportInfoDto fromEntity(AirportEntity entity) {
        return new AirportInfoDto(entity.getCity(), entity.getCountry(), entity.getName(), entity.getCode());
    }
}
