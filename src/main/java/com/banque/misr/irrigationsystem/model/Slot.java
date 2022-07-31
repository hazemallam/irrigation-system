package com.banque.misr.irrigationsystem.model;

import com.banque.misr.irrigationsystem.model.enums.SlotStatus;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "SLOT")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "PLOT_ID", referencedColumnName = "ID")
    private Plot plot;

    @Column(name = "TIME_SLOT")
    private LocalTime period;

    @Column(name = "START_DATE")
    private Date date;

    @Column(name = "AMOUNT_OF_WATER")
    private double amountOWater;

    @Column(name = "STATUS")
    private SlotStatus slotStatus;

    @OneToOne
    @JoinColumn(name = "SENSOR", referencedColumnName = "ID")
    private Sensor sensor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public LocalTime getPeriod() {
        return period;
    }

    public void setPeriod(LocalTime period) {
        this.period = period;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmountOWater() {
        return amountOWater;
    }

    public void setAmountOWater(double amountOWater) {
        this.amountOWater = amountOWater;
    }

    public SlotStatus getStatus() {
        return slotStatus;
    }

    public void setStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
