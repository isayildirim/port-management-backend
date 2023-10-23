package com.thy.portmanagement.entity;

import jakarta.persistence.Entity;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Document(indexName = "airports")
public class AirportEntity extends BaseEntity {

    @Field(type = FieldType.Text, name = "city")
    private String city;

    @Field(type = FieldType.Text, name = "code")
    private String code;

    @Field(type = FieldType.Text, name = "country")
    private String country;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    public AirportEntity(String city, String code, String country, String name) {
        this.city = city;
        this.code = code;
        this.country = country;
        this.name = name;
    }

    public AirportEntity() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
