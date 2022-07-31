package com.banque.misr.irrigationsystem.dto;

import java.util.Date;

public class PlotRequest {

    private long crop;
    private double area;

    private Date cultivatedDate;

    public long getCrop() {
        return crop;
    }

    public void setCrop(long crop) {
        this.crop = crop;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Date getCultivatedDate() {
        return cultivatedDate;
    }

    public void setCultivatedDate(Date cultivatedDate) {
        this.cultivatedDate = cultivatedDate;
    }
}
