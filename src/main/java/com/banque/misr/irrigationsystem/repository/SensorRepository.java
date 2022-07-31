package com.banque.misr.irrigationsystem.repository;

import com.banque.misr.irrigationsystem.model.Plot;
import com.banque.misr.irrigationsystem.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Sensor findByPlotId(Plot plotId);
}
