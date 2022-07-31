package com.banque.misr.irrigationsystem.dto;

public class CropRequest {
    private String name;

    private double waterAmountPerSeason;

    private int totalGrowingPeriod;

    private int daysBetweenEachPeriod;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWaterAmountPerSeason() {
        return waterAmountPerSeason;
    }

    public void setWaterAmountPerSeason(double waterAmountPerSeason) {
        this.waterAmountPerSeason = waterAmountPerSeason;
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
