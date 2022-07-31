package com.banque.misr.irrigationsystem.service;

import com.banque.misr.irrigationsystem.model.Plot;
import com.banque.misr.irrigationsystem.model.Sensor;
import com.banque.misr.irrigationsystem.model.Slot;
import com.banque.misr.irrigationsystem.model.enums.SlotStatus;
import com.banque.misr.irrigationsystem.repository.PlotRepository;
import com.banque.misr.irrigationsystem.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SlotService {
    private final SlotRepository slotRepository;
    private final SensorService sensorService;

    private final AlertService alertService;

    @Autowired
    private PlotRepository plotRepository;

    public SlotService(SlotRepository slotRepository, SensorService sensorService, AlertService alertService) {
        this.slotRepository = slotRepository;
        this.sensorService = sensorService;
        this.alertService = alertService;
    }

    public void sendSignalToSensor(long slotId) throws InterruptedException{
        Slot slot = slotRepository.findById(slotId).orElseThrow();
        boolean isIrrigated = sensorService.irrigate(slot.getSensor().getId());
        if(isIrrigated) {
            slot.setStatus(SlotStatus.SUCCEEDED);
            slotRepository.saveAndFlush(slot);
        }
        else{
            for(int i = 0; i< 5; i++){
                Thread.sleep(500L); // to simulate retry calling to sensor each 0.5s for 5 times
                isIrrigated = sensorService.irrigate(slot.getSensor().getId());
                if(isIrrigated){
                    slot.setStatus(SlotStatus.SUCCEEDED);
                    slotRepository.saveAndFlush(slot);
                    break;
                }

            }
            if(!isIrrigated){
                slot.setStatus(SlotStatus.FAILED);
                slotRepository.saveAndFlush(slot);
                alertService.fire();
            }
        }

    }
    private int computeNumberOfSlots(double totalAmountOfWater, int totalGrowingPeriod, int days){
        int periods = totalGrowingPeriod / days;
        return Math.round((float) totalAmountOfWater/ periods) / 1000;
    }

    public String configureSlotsAutomatically(long plotId){
        Plot plot = plotRepository.findById(plotId).orElseThrow();
        slotRepository.saveAllAndFlush(computeSlots(plot));
        return "Slots configured automatically";
    }
    private List<Slot> computeSlots(Plot plot){
        CompletableFuture<Sensor> sensorCompletableFuture = CompletableFuture.supplyAsync(() -> sensorService.findByPlotId(plot));
        int numberOfSlots = computeNumberOfSlots(plot.getTotalAmountOfWater(), plot.getCrop().getTotalGrowingPeriod(), plot.getCrop().getDaysBetweenEachPeriod());
        double amountOfWaterForEachSlot = plot.getTotalAmountOfWater() / numberOfSlots;
        List<Slot> slots = new ArrayList<>();
        long factor = 1L; // assuming that each mm of water takes 1s to be pumped to a plot;
        LocalDate localDate = computeDate(plot);
        for(int i = 0; i < numberOfSlots; i++){
            Slot slot = new Slot();
            slot.setPlot(plot);
            slot.setAmountOWater(amountOfWaterForEachSlot);
            localDate = localDate.plus(plot.getCrop().getDaysBetweenEachPeriod(), ChronoUnit.DAYS);
            slot.setDate(Date.from(LocalDateTime.of(localDate, LocalTime.of(2, 30, 20)).toInstant(ZoneOffset.UTC)));
            slot.setPeriod(computeTimePeriod(amountOfWaterForEachSlot, factor));
            slot.setSensor(sensorCompletableFuture.join());
            slot.setStatus(null);
            slots.add(slot);
        }
        return slots;
    }

    private LocalDate computeDate(Plot plot) {
        Date cultivatedDate = plot.getCultivatedDate();
        Instant instant = cultivatedDate.toInstant();
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        return localDate.minus(plot.getCrop().getDaysBetweenEachPeriod(), ChronoUnit.DAYS);
    }

    private LocalTime computeTimePeriod(double amountOfWaterForEachSlot, long factor) {
        long timePeriod = Math.round(amountOfWaterForEachSlot * factor);
        return LocalTime.ofSecondOfDay(timePeriod);
    }
    private List<Slot> getSlots(){
        return slotRepository.findAll();
    }

}
