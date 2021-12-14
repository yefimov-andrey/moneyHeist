package com.testtask.moneyheist.controllers;

import com.testtask.moneyheist.objects.Heist;
import com.testtask.moneyheist.services.HeistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class HeistController {

    private final HeistService heistService;

    @RequestMapping(value = "/heist", method = RequestMethod.POST)
    public ResponseEntity createHeist(@RequestBody Heist heist){
        if(!heistService.existsByName(heist.getName())) {
            if(heist.getStartTime().isBefore(heist.getEndTime())) {
                Long id = heistService.createHeist(heist);
                return ResponseEntity.created(URI.create("/heist/" + id)).build();
            }
            else
                return ResponseEntity.badRequest().body("End time is before the start time!");
        }
        else
            return ResponseEntity.badRequest().body("Name already exists!");
    }

}
