package com.banque.misr.irrigationsystem.service;

import com.banque.misr.irrigationsystem.dto.SensorRequest;
import com.banque.misr.irrigationsystem.model.Plot;
import com.banque.misr.irrigationsystem.model.Sensor;
import com.banque.misr.irrigationsystem.model.enums.SensorStatus;
import com.banque.misr.irrigationsystem.repository.PlotRepository;
import com.banque.misr.irrigationsystem.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    private final PlotRepository plotRepository;

    public SensorService(SensorRepository sensorRepository, PlotRepository plotRepository) {
        this.sensorRepository = sensorRepository;
        this.plotRepository = plotRepository;
    }


    public boolean irrigate(long slotId){
       Sensor sensor = sensorRepository.findById(slotId).orElseThrow();
       return sensor.getSensorStatus().equals(SensorStatus.SUCCEEDED);
    }

    public String addSensor(SensorRequest sensorRequest){
        try{
            CompletableFuture<Plot> plotCompletableFuture = CompletableFuture.supplyAsync(() -> plotRepository.findById(sensorRequest.getPlotId()).orElseThrow(()-> new NoSuchElementException("Plot not found...")));
            Sensor sensor = new Sensor();
            sensor.setName(sensorRequest.getName());
            sensor.setSensorStatus(sensorRequest.getSensorStatus());
            sensor.setPlotId(plotCompletableFuture.join());
            Sensor addedSensor = sensorRepository.saveAndFlush(sensor);
            return String.format("sensor added successfully with Id %s", addedSensor.getId());

        } catch (Exception ex){
            return ex.getMessage();
        }

    }

    public Sensor findByPlotId(Plot plotId){
        return sensorRepository.findByPlotId(plotId);
    }

    public Sensor findById(long plotId){
        return sensorRepository.findById(plotId).orElseThrow();
    }
}
