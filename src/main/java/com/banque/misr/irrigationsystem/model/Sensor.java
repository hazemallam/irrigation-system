package com.banque.misr.irrigationsystem.model;

import com.banque.misr.irrigationsystem.model.enums.SensorStatus;

import javax.persistence.*;

@Entity
@Table(name = "SENSOR")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS", nullable = false)
    private SensorStatus sensorStatus;

    @OneToOne
    @JoinColumn(name = "PLOT_ID", referencedColumnName = "ID", unique = true)
    private Plot plotId;

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

    public Plot getPlotId() {
        return plotId;
    }

    public void setPlotId(Plot plotId) {
        this.plotId = plotId;
    }
}
