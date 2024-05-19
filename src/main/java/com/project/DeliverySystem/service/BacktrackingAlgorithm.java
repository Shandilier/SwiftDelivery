package com.project.DeliverySystem.service;

import com.project.DeliverySystem.model.DeliveryGraphWithMap;
import com.project.DeliverySystem.model.DeliveryNode;
import com.project.DeliverySystem.model.PickUpNode;
import com.project.DeliverySystem.model.interfaces.Node;
import com.project.DeliverySystem.service.interfaces.Algorithm;
import com.project.DeliverySystem.store.DeliveryStore;
import com.project.DeliverySystem.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class BacktrackingAlgorithm implements Algorithm {

    Logger LOGGER = LoggerFactory.getLogger(BacktrackingAlgorithm.class);
    @Autowired
    PickUpNode pickUpNode;

    @Autowired
    DeliveryNode deliveryNode;

    @Autowired
    DeliveryStore deliveryStore;

    @Autowired
    DeliveryGraphWithMap graphWithMap;

    @Override
    public void getShortestPath(){
        Set<String> restaurants = deliveryStore.getRestaurants();
        boolean[] visitedRestaurant = new boolean[restaurants.size()];
        boolean[] visitedCustomer = new boolean[restaurants.size()];

        for(String restaurant: restaurants){
            Arrays.fill(visitedRestaurant, false);
            Arrays.fill(visitedCustomer, false);
            LOGGER.info(String.format("Starting new possible path from restaurant: %s",restaurant));
            int restaurantId = Integer.parseInt(restaurant.substring(0,restaurant.length()-1));
            visitedRestaurant[restaurantId] = true;
            // Function call
            getShortestPath(restaurant, visitedRestaurant, visitedCustomer, 0, 0);

            LOGGER.info(String.format("Shortest delivery time till now is %s minutes.",restaurant));
        }
    }
    public void getShortestPath(String pointName, boolean[] visitedRestaurant, boolean[] visitedCustomer, int totalTime, int totalOrderCompleted) {
        if(totalOrderCompleted == visitedRestaurant.length){
            if(totalTime< deliveryStore.getShortestTime()){
                LOGGER.info(String.format("Found one path with optimal time of %s minutes.",totalTime));
                deliveryStore.setShortestTime(totalTime);
            }
            return;
        }
        // Neighbour nodes for the current node. Node here is the pickup and delivery locations
        List<Node> neighbourNodes = graphWithMap.getDirections().get(pointName);
        int neighbour;
        String neighbourType;
        int travelTime;

        for(Node node: neighbourNodes){
            if(node instanceof PickUpNode){
                pickUpNode = (PickUpNode) node;
                neighbour = pickUpNode.getNeighbour();
                neighbourType = pickUpNode.getType();
                travelTime = pickUpNode.getWeight();
            }
            else {
                deliveryNode = (DeliveryNode) node;
                neighbour = deliveryNode.getNeighbour();
                neighbourType = deliveryNode.getType();
                travelTime = deliveryNode.getWeight();
            }

            if(visitedRestaurant[neighbour] && Constants.CUSTOMER.equalsIgnoreCase(neighbourType))
                continue;
            if(!visitedRestaurant[neighbour] && Constants.RESTAURANT.equalsIgnoreCase(neighbourType)){
                visitedRestaurant[neighbour] = true;
            }
            int flag2 = 0;
            if(visitedRestaurant[neighbour]){
                if(!visitedCustomer[neighbour] && Constants.CUSTOMER.equalsIgnoreCase(neighbourType)){
                    visitedCustomer[neighbour] = true;
                    flag2=1;
                }
            }

            // Recursive function call to get the shortest travel time
            getShortestPath(String.valueOf(neighbour)+neighbourType, visitedRestaurant, visitedCustomer,
                    totalTime+travelTime, totalOrderCompleted+flag2);

            // Backtrack
            if(visitedRestaurant[neighbour] && Constants.RESTAURANT.equalsIgnoreCase(neighbourType)){
                visitedRestaurant[neighbour] = false;
            }
            if(visitedRestaurant[neighbour]){
                if(visitedCustomer[neighbour] && Constants.CUSTOMER.equalsIgnoreCase(neighbourType)){
                    visitedCustomer[neighbour] = false;
                }
            }
        }
    }
}
