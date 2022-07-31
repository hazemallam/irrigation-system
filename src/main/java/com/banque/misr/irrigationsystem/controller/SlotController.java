package com.banque.misr.irrigationsystem.controller;

import com.banque.misr.irrigationsystem.dto.ConfigureRequest;
import com.banque.misr.irrigationsystem.dto.IrrigateRequest;
import com.banque.misr.irrigationsystem.service.SlotService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slot/sensor")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @RequestMapping(value = "/irrigate", method = RequestMethod.POST)
    public String irrigate(@RequestBody IrrigateRequest irrigateRequest){
        try {
            slotService.sendSignalToSensor(irrigateRequest.getSlotId());
            return "successfully irrigated";
        } catch (Exception ex){
            return ex.getMessage();
        }
    }

    @RequestMapping(value = "/configure-automatically", method = RequestMethod.POST)
    public String configureAutomatically(@RequestBody ConfigureRequest configureRequest){
        return slotService.configureSlotsAutomatically(configureRequest.getPlotId());
    }
}
