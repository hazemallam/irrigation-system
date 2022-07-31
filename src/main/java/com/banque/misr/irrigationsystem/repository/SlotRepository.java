package com.banque.misr.irrigationsystem.repository;

import com.banque.misr.irrigationsystem.model.Plot;
import com.banque.misr.irrigationsystem.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    Optional<List<Slot>> findByplot(Plot plot);
}
