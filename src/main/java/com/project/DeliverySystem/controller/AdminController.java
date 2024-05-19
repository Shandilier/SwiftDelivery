package com.project.DeliverySystem.controller;

import com.project.DeliverySystem.Exception.NodeConnectionException;
import com.project.DeliverySystem.annotation.Auth;
import com.project.DeliverySystem.model.DeliveryGraphWithMap;
import com.project.DeliverySystem.store.DeliveryStore;
import com.project.DeliverySystem.utils.Constants;
import com.project.DeliverySystem.utils.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {

    @Autowired
    DeliveryStore deliveryStore;

    @Autowired
    DeliveryGraphWithMap deliveryGraphWithMap;

    Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    // For admin
    @Auth
    @PostMapping(value = "/addJunctions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> connectJunctions(Map<String, Object> coordinateDetails){
        try{
            // In case of 2-D coordinates, we can include coordinates as well and
            // calculate time from source to destination
            int source = (int) coordinateDetails.get("source");
            int destination = (int) coordinateDetails.get("destination");

            // Getting time as input, but we can use any standard method to calculate the distance
            int time = (int) coordinateDetails.get("time");
            String sourceType = (String) coordinateDetails.getOrDefault("sourceType", "");
            String destinationType = (String) coordinateDetails.getOrDefault("destinationType", "");
            int foodPreparationTime = (int) coordinateDetails.getOrDefault("foodPreparationTime", 0);

            LOGGER.info("New nodes data received.");
            LOGGER.info(String.format(coordinateDetails.toString()));

            // Function call to connect nodes/junctions
            deliveryGraphWithMap.addEdge(source, destination, time, sourceType, destinationType, foodPreparationTime);

            // Adding new restaurant to the restaurant list
            if(sourceType.equals(Constants.RESTAURANT)){
                String restaurantName = String.valueOf(source)+Constants.RESTAURANT;
                deliveryStore.getRestaurants().add(restaurantName);
            }
            return ResponseMapper.getResponse(Constants.SUCCESS, "Nodes connectivity successful");
        }
        catch (NodeConnectionException e){
            return ResponseMapper.getResponse(Constants.ERROR, e.getMessage());
        }
    }
}
