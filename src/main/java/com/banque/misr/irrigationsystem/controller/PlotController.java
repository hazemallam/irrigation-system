package com.banque.misr.irrigationsystem.controller;

import com.banque.misr.irrigationsystem.dto.EditPlotRequest;
import com.banque.misr.irrigationsystem.dto.PlotRequest;
import com.banque.misr.irrigationsystem.dto.PlotResponse;
import com.banque.misr.irrigationsystem.dto.Response;
import com.banque.misr.irrigationsystem.model.Plot;
import com.banque.misr.irrigationsystem.service.PlotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plot")
public class PlotController {
    private final PlotService plotService;

    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addPlot(@RequestBody PlotRequest plotRequest){
        return plotService.addPlot(plotRequest);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Response> getPlotsDetails(){
        return plotService.getPlotsDetails();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String editPlot(@RequestBody EditPlotRequest editPlotRequest){
        return plotService.editPlot(editPlotRequest);
    }

}
