package com.banque.misr.irrigationsystem.dto;

public class CropResponse {
    private long id;

    private String name;

    private double waterAmount;

    private int totalGrowingPeriod;

    private int daysBetweenEachPeriod;

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

    public double getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(double waterAmount) {
        this.waterAmount = waterAmount;
    }

    public int getTotalGrowingPeriod() {
        return totalGrowingPeriod;
    }

    public void setTotalGrowingPeriod(int totalGrowingPeriod) {
        this.totalGrowingPeriod = totalGrowingPeriod;
    }

    public int getDaysBetweenEachPeriod() {
        return daysBetweenEachPeriod;
    }

    public void setDaysBetweenEachPeriod(int daysBetweenEachPeriod) {
        this.daysBetweenEachPeriod = daysBetweenEachPeriod;
    }
}
