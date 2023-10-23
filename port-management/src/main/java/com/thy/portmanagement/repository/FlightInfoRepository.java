package com.thy.portmanagement.repository;

import com.thy.portmanagement.entity.FlightInfoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface FlightInfoRepository extends ElasticsearchRepository<FlightInfoEntity, String> {

    List<FlightInfoEntity> findByArrivalAirportAndDepartureAirport(String arrivalAirport, String departureAirport);

}
