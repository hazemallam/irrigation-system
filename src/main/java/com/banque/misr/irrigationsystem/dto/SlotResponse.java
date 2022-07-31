package com.banque.misr.irrigationsystem.dto;

import com.banque.misr.irrigationsystem.model.enums.SlotStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class SlotResponse {

    private long id;

    private LocalTime period;

    private LocalDate date;

    private double amountOWater;

    private SlotStatus slotStatus;

    private SensorResponse sensor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public LocalTime getPeriod() {
        return period;
    }

    public void setPeriod(LocalTime period) {
        this.period = period;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmountOWater() {
        return amountOWater;
    }

    public void setAmountOWater(double amountOWater) {
        this.amountOWater = amountOWater;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public SensorResponse getSensor() {
        return sensor;
    }

    public void setSensor(SensorResponse sensor) {
        this.sensor = sensor;
    }
}
