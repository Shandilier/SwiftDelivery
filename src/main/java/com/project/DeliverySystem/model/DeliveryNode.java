package com.project.DeliverySystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.DeliverySystem.model.interfaces.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;



@NoArgsConstructor
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryNode implements Node {

    private int neighbour;
    private int weight;
    private String type;
    private int foodPreparationTime;

    public DeliveryNode(int neighbour, int weight, String type) {
        this.neighbour = neighbour;
        this.weight = weight;
        this.type = type;
    }

    public int getNeighbour() {
        return neighbour;
    }

    public void setNeighbour(int neighbour) {
        this.neighbour = neighbour;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DeliveryNode{" +
                "neighbour=" + neighbour +
                ", weight=" + weight +
                ", type='" + type + '\'' +
                '}';
    }
}
