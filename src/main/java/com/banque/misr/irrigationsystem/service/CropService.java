package com.banque.misr.irrigationsystem.service;

import com.banque.misr.irrigationsystem.dto.CropRequest;
import com.banque.misr.irrigationsystem.model.Crop;
import com.banque.misr.irrigationsystem.repository.CropRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CropService {
    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }
    public String addCrop(@RequestBody CropRequest cropRequest){
        Crop crop = new Crop();
        crop.setName(cropRequest.getName());
        crop.setWaterAmount(cropRequest.getWaterAmountPerSeason());
        crop.setDaysBetweenEachPeriod(cropRequest.getDaysBetweenEachPeriod());
        crop.setTotalGrowingPeriod(cropRequest.getTotalGrowingPeriod());
        Crop savedCrop = cropRepository.save(crop);
        return String.format("Crop added successfully and its id is %d", savedCrop.getId());
    }
    public void addCrops(@RequestBody List<CropRequest> cropRequests){
        List<Crop> crops = cropRequests.stream().map(this::mapToCrop).collect(Collectors.toList());
        cropRepository.saveAll(crops);
    }

    private Crop mapToCrop(CropRequest cropRequest) {
        Crop crop = new Crop();
        crop.setName(cropRequest.getName());
        crop.setWaterAmount(cropRequest.getWaterAmountPerSeason());
        return crop;
    }

    public List<Crop> getCrops(){
        return cropRepository.findAll();
    }

    public Crop getCrop(long id){
        return cropRepository.findById(id).orElseThrow();
    }
}
