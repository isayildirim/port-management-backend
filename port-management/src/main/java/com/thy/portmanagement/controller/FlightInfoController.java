package com.thy.portmanagement.controller;

import com.thy.portmanagement.controller.validator.CheckAirportValue;
import com.thy.portmanagement.dto.AirportInfoDto;
import com.thy.portmanagement.dto.FlightInfoDto;
import com.thy.portmanagement.dto.FlightInfoReq;
import com.thy.portmanagement.service.AirportInfoService;
import com.thy.portmanagement.service.FlightInfoService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class FlightInfoController {

    private final FlightInfoService flightInfoService;
    private final AirportInfoService airportInfoService;

    public FlightInfoController(FlightInfoService flightInfoService,
                                AirportInfoService airportInfoService) {
        this.flightInfoService = flightInfoService;
        this.airportInfoService = airportInfoService;
    }

    @GetMapping("/flight/{airportValue}")
    @RateLimiter(name = "basic")
    public ResponseEntity<List<AirportInfoDto>> getAirportInfo(@PathVariable("airportValue") @NotNull @CheckAirportValue String airportValue) {

        return ResponseEntity.ok(airportInfoService.getAirportInfo(airportValue));
    }

    @PostMapping("/flight-info")
    @RateLimiter(name = "basic")
    public ResponseEntity<List<FlightInfoDto>> getFlightInfo(@RequestBody FlightInfoReq req) {
        return ResponseEntity.ok(flightInfoService.getFlightInfo(req));
    }

    @GetMapping("/all-flights")
    @RateLimiter(name = "basic")
    public ResponseEntity<List<FlightInfoDto>> getAllFlightInfo() {
        return ResponseEntity.ok(flightInfoService.getAllFlightInfo());
    }

    @GetMapping("/all-airports")
    @RateLimiter(name = "basic")
    public ResponseEntity<List<AirportInfoDto>> getAllAirportInfo() {
        return ResponseEntity.ok(airportInfoService.getAllAirportInfo());
    }

}
