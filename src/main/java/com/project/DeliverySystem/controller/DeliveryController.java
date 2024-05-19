package com.project.DeliverySystem.controller;


import com.project.DeliverySystem.Exception.NodeConnectionException;
import com.project.DeliverySystem.model.DeliveryGraphWithMap;
import com.project.DeliverySystem.service.BacktrackingAlgorithm;
import com.project.DeliverySystem.store.DeliveryStore;
import com.project.DeliverySystem.utils.Constants;
import com.project.DeliverySystem.utils.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1")
public class DeliveryController {

    Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);
    @Autowired
    DeliveryStore deliveryStore;

    @Autowired
    BacktrackingAlgorithm backtrackingAlgorithm;

    @Autowired
    DeliveryGraphWithMap deliveryGraphWithMap;

    // For the delivery agents
    @GetMapping(value = "/getMinimumTime", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getShortestTimeToDeliver(){
        try{
            backtrackingAlgorithm.getShortestPath();
            int shortestTime = deliveryStore.getShortestTime();
            return ResponseEntity.status(HttpStatus.OK).body(shortestTime);
        }
        catch (Exception e) {
            LOGGER.info("Exception occurred while running algorithm.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
