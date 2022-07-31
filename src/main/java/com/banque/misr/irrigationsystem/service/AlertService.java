package com.banque.misr.irrigationsystem.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class AlertService {
    public void fire() throws InterruptedException{
        throw new InterruptedException("Forbidden Sensor Please Check it.....");
    }
}
