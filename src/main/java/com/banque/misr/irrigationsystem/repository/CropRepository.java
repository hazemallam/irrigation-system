package com.banque.misr.irrigationsystem.repository;

import com.banque.misr.irrigationsystem.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
}
