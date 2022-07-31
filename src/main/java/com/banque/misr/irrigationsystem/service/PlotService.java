package com.banque.misr.irrigationsystem.service;

import com.banque.misr.irrigationsystem.dto.*;
import com.banque.misr.irrigationsystem.model.Crop;
import com.banque.misr.irrigationsystem.model.Plot;
import com.banque.misr.irrigationsystem.model.Sensor;
import com.banque.misr.irrigationsystem.model.Slot;
import com.banque.misr.irrigationsystem.repository.CropRepository;
import com.banque.misr.irrigationsystem.repository.PlotRepository;
import com.banque.misr.irrigationsystem.repository.SlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PlotService {
    private final PlotRepository plotRepository;
    private final CropRepository cropRepository;

    private final SlotRepository slotRepository;

    private final SlotService slotService;

    public PlotService(PlotRepository plotRepository, CropRepository cropRepository, SlotRepository slotRepository, SlotService slotService) {
        this.plotRepository = plotRepository;
        this.cropRepository = cropRepository;
        this.slotRepository = slotRepository;
        this.slotService = slotService;
    }

    public String addPlot(@RequestBody PlotRequest plotRequest) {
        CompletableFuture<Crop> cropCompletableFuture = CompletableFuture.supplyAsync(() -> cropRepository.findById(plotRequest.getCrop()).orElseThrow());
        Plot plot = new Plot();
        plot.setArea(plotRequest.getArea());
        Crop crop = cropCompletableFuture.join();
        double totalAmountOfWater = crop.getWaterAmount() * plotRequest.getArea();
        plot.setCrop(crop);
        plot.setTotalAmountOfWater(totalAmountOfWater);
        plot.setCultivatedDate(plotRequest.getCultivatedDate());
        Plot savedPlot = plotRepository.saveAndFlush(plot);
        return String.format("Plot added successfully and its Id is %s", savedPlot.getId());
    }

    public String editPlot(@RequestBody EditPlotRequest editPlotRequest){
        CompletableFuture<Plot> plotCompletableFuture = CompletableFuture.supplyAsync(() -> plotRepository.findById(editPlotRequest.getId()).orElseThrow());
        CompletableFuture<Crop> cropCompletableFuture = CompletableFuture.supplyAsync(() -> cropRepository.findById(editPlotRequest.getCrop()).orElseThrow());
        Plot plotFromDB = plotCompletableFuture.join();
        CompletableFuture<Optional<List<Slot>>> optionalCompletableFuture = CompletableFuture.supplyAsync(() -> slotRepository.findByplot(plotFromDB));
        plotFromDB.setCultivatedDate(editPlotRequest.getCultivatedDate());
        plotFromDB.setArea(editPlotRequest.getArea());
        Crop crop = cropCompletableFuture.join();
        double totalAmountOfWater = crop.getWaterAmount() * editPlotRequest.getArea();
        plotFromDB.setTotalAmountOfWater(totalAmountOfWater);
        plotFromDB.setCrop(crop);
        plotRepository.save(plotFromDB);
        Optional<List<Slot>> optionalSlots = optionalCompletableFuture.join();
        if(optionalSlots.isPresent()){
            slotRepository.deleteAll(optionalSlots.get());
            slotService.configureSlotsAutomatically(editPlotRequest.getId());
        }
        return "Plot Edited Successfully";

    }

    public List<Response> getPlotsDetails(){
        return plotRepository.findAll().stream().map(plot -> {
            CompletableFuture<Optional<List<Slot>>> optionalCompletableFuture = CompletableFuture.supplyAsync(() -> slotRepository.findByplot(plot));
            Response response = new Response();
            response.setId(plot.getId());
            response.setArea(plot.getArea());
            response.setCrop(plot.getCrop());
            Date cultivatedDate = plot.getCultivatedDate();
            Instant instant = cultivatedDate.toInstant();
            LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
            response.setCultivatedDate(localDate);
            response.setTotalAmountOfWater(plot.getTotalAmountOfWater());
            Optional<List<Slot>> optionalSlots = optionalCompletableFuture.join();
            if(optionalSlots.isPresent()) {
                List<SlotResponse> slots = optionalSlots.get().stream().map(eachSlot -> {
                    SlotResponse slotResponse = new SlotResponse();
                    SensorResponse sensorResponse = new SensorResponse();
                    slotResponse.setSlotStatus(eachSlot.getStatus());
                    slotResponse.setAmountOWater(eachSlot.getAmountOWater());
                    Date slotDate = eachSlot.getDate();
                    Instant slotDateInstant = slotDate.toInstant();
                    LocalDate slotLocalDate = LocalDate.ofInstant(slotDateInstant, ZoneId.systemDefault());
                    slotResponse.setDate(slotLocalDate);
                    slotResponse.setId(eachSlot.getId());
                    slotResponse.setPeriod(eachSlot.getPeriod());
                    Sensor sensor = eachSlot.getSensor();
                    sensorResponse.setId(sensor.getId());
                    sensorResponse.setName(sensor.getName());
                    sensorResponse.setSensorStatus(sensor.getSensorStatus());
                    slotResponse.setSensor(sensorResponse);
                    return slotResponse;
                }).collect(Collectors.toList());
                response.setSlots(slots);
            }
            return response;
        }).collect(Collectors.toList());
    }

}
