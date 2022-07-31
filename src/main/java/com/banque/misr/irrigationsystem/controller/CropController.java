package com.banque.misr.irrigationsystem.controller;

import com.banque.misr.irrigationsystem.dto.CropRequest;
import com.banque.misr.irrigationsystem.model.Crop;
import com.banque.misr.irrigationsystem.service.CropService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crop")
public class CropController {
    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addCrop(@RequestBody CropRequest cropRequest){
        return cropService.addCrop(cropRequest);
    }

    @RequestMapping(value = "/addCrops", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCrops(@RequestBody List<CropRequest> cropRequests){
    cropService.addCrops(cropRequests);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Crop> getCrops(){
        return cropService.getCrops();
    }

}