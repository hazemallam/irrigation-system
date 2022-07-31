package com.banque.misr.irrigationsystem.dto;

import com.banque.misr.irrigationsystem.model.enums.SensorStatus;

public class SensorRequest {

    private String name;

    private SensorStatus sensorStatus;

    private long plotId;

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

    public long getPlotId() {
        return plotId;
    }

    public void setPlotId(long plotId) {
        this.plotId = plotId;
    }
}
