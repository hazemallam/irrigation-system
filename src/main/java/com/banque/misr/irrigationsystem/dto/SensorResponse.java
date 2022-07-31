package com.banque.misr.irrigationsystem.dto;

import com.banque.misr.irrigationsystem.model.enums.SensorStatus;

public class SensorResponse {

    private long id;

    private String name;

    private SensorStatus sensorStatus;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorStatus getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(SensorStatus sensorStatus) {
        this.sensorStatus = sensorStatus;
    }
}
