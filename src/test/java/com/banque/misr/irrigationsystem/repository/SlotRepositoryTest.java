package com.banque.misr.irrigationsystem.repository;

import com.banque.misr.irrigationsystem.model.Crop;
import com.banque.misr.irrigationsystem.model.Plot;
import com.banque.misr.irrigationsystem.model.Sensor;
import com.banque.misr.irrigationsystem.model.Slot;
import com.banque.misr.irrigationsystem.model.enums.SensorStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class SlotRepositoryTest {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private SensorRepository sensorRepository;


    @Test
    void itShouldCheckIfListOfSlotsExits() {
        //given
        Crop crop = new Crop();
        crop.setTotalGrowingPeriod(100);
        crop.setName("Tomato");
        crop.setWaterAmount(400);
        crop.setDaysBetweenEachPeriod(10);
        Crop savedCrop = cropRepository.save(crop);

        Plot plot = new Plot();
        plot.setArea(100);
        plot.setCrop(savedCrop);
        plot.setCultivatedDate(Date.valueOf("2022-07-20"));
        Plot savedPlot = plotRepository.save(plot);

        Sensor sensor = new Sensor();
        sensor.setName("sensor 1");
        sensor.setPlotId(savedPlot);
        sensor.setSensorStatus(SensorStatus.SUCCEEDED);
        Sensor savedSensor = sensorRepository.save(sensor);

        Slot slotToBeSaved = new Slot();
        slotToBeSaved.setStatus(null);
        slotToBeSaved.setPlot(savedPlot);
        slotToBeSaved.setSensor(savedSensor);
        slotToBeSaved.setAmountOWater(551151);
        slotToBeSaved.setPeriod(LocalTime.of(2, 30, 50));
        slotRepository.save(slotToBeSaved);

        //when
        Optional<List<Slot>> byplot = slotRepository.findByplot(savedPlot);
        List<Slot> slots = byplot.get();

        //then
        slots.forEach(slot -> {
            assertThat(slot.getPlot().getId()).isEqualTo(savedPlot.getId());
        });
    }
}