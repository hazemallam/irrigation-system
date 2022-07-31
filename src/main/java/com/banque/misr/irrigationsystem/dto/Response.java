package com.banque.misr.irrigationsystem.dto;

import com.banque.misr.irrigationsystem.model.Crop;
import com.banque.misr.irrigationsystem.model.Slot;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Response {

    private long id;

    private double area;

    private double totalAmountOfWater;

    private LocalDate cultivatedDate;

    private Crop crop;

    private List<SlotResponse> slots;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getTotalAmountOfWater() {
        return totalAmountOfWater;
    }

    public void setTotalAmountOfWater(double totalAmountOfWater) {
        this.totalAmountOfWater = totalAmountOfWater;
    }

    public LocalDate getCultivatedDate() {
        return cultivatedDate;
    }

    public void setCultivatedDate(LocalDate cultivatedDate) {
        this.cultivatedDate = cultivatedDate;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public List<SlotResponse> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotResponse> slots) {
        this.slots = slots;
    }
}
