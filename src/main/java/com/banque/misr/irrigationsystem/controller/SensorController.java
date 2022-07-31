package com.banque.misr.irrigationsystem.controller;

import com.banque.misr.irrigationsystem.dto.SensorRequest;
import com.banque.misr.irrigationsystem.model.Sensor;
import com.banque.misr.irrigationsystem.repository.SensorRepository;
import com.banque.misr.irrigationsystem.service.SensorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSensor(@RequestBody SensorRequest sensorRequest){
        return sensorService.addSensor(sensorRequest);
    }
}
