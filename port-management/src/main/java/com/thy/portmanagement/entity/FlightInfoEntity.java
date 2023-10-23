package com.thy.portmanagement.entity;

import jakarta.persistence.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Document(indexName = "flight_infos")
public class FlightInfoEntity extends BaseEntity {

    @Field(type = FieldType.Text, name = "departure_airport")
    private String departureAirport;

    @Field(type = FieldType.Text, name = "arrival_airport")
    private String arrivalAirport;

    @Field(type = FieldType.Text, name = "departure_time")
    private String departureTime;

    @Field(type = FieldType.Text, name = "arrival_time")
    private String arrivalTime;

    @Field(type = FieldType.Text, name = "baggage_allowance")
    private int baggageAllowance;

    @Field(type = FieldType.Text, name = "price")
    private String price;

    @Field(type = FieldType.Text, name = "has_stop")
    private boolean hasStop = false;

    public FlightInfoEntity() {
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBaggageAllowance() {
        return baggageAllowance;
    }

    public void setBaggageAllowance(int baggageAllowance) {
        this.baggageAllowance = baggageAllowance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isHasStop() {
        return hasStop;
    }

    public void setHasStop(boolean hasStop) {
        this.hasStop = hasStop;
    }
}
