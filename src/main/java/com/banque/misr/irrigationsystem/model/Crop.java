package com.banque.misr.irrigationsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "CROP")
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "REFERENCE_WATER_AMOUNT_PER_SEASON", nullable = false)
    private double waterAmount;

    @Column(name = "TOTAL_GROWING_PERIOD", nullable = false)
    private int totalGrowingPeriod;

    @Column(name = "RECOMMENDED_DAYS_BETWEEN_EACH_IRRIGATION_PERIOD", nullable = false)
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
