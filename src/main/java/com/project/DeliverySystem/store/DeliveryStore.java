package com.project.DeliverySystem.store;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DeliveryStore {

    private int shortestTime = Integer.MAX_VALUE;
    private Set<String> restaurants;

    public int getShortestTime() {
        return shortestTime;
    }

    public void setShortestTime(int shortestTime) {
        this.shortestTime = shortestTime;
    }

    public Set<String> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<String> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "DeliveryStore{" +
                "shortestTime=" + shortestTime +
                '}';
    }
}
