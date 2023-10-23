package com.thy.portmanagement.repository;

import com.thy.portmanagement.entity.AirportEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends ElasticsearchRepository<AirportEntity, String> {

    List<AirportEntity> findByCityContainingOrCodeContaining(String city, String code);

    Optional<AirportEntity> findByCode(String code);
}
