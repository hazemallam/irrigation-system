package com.banque.misr.irrigationsystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "PLOT")
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "CROP_ID", referencedColumnName = "ID", nullable = false)
    private Crop crop;

    @Column(name = "AREA", nullable = false)
    private double area;

    @Column(name = "TOTAL_AMOUNT_OF_WATER", nullable = false)
    private double totalAmountOfWater;

    @Column(name = "CULTIVATED_DATE", nullable = false)
    private Date cultivatedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
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

    public Date getCultivatedDate() {
        return cultivatedDate;
    }

    public void setCultivatedDate(Date cultivatedDate) {
        this.cultivatedDate = cultivatedDate;
    }
}
