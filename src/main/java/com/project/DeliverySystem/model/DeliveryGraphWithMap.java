package com.project.DeliverySystem.model;

import com.project.DeliverySystem.Exception.NodeConnectionException;
import com.project.DeliverySystem.model.interfaces.Graph;
import com.project.DeliverySystem.model.interfaces.Node;
import com.project.DeliverySystem.utils.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@NoArgsConstructor
@Component
@Getter
@Setter
public class DeliveryGraphWithMap implements Graph {

    Logger LOGGER = LoggerFactory.getLogger(DeliveryGraphWithMap.class);

    private int v;
    private Map<String, List<Node>> directions = new HashMap<>();

    DeliveryGraphWithMap(int v) {
        this.v = v;
    }

    /*
        This function is used to add an edge between two nodes
     */
    @Override
    public void addEdge(int x, int y, int weight, String nodeAType, String nodeBType) throws NodeConnectionException {
        String nodeA = String.valueOf(x)+nodeAType;
        String nodeB = String.valueOf(y)+nodeBType;
        LOGGER.info(String.format("Connecting nodes %s and %s", nodeA, nodeB));
        try {
            switch (nodeAType) {
                case "Restaurant":
                    if (directions.containsKey(nodeA)) {
                        directions.get(nodeA).add(new PickUpNode(y, weight, nodeBType));
                    } else {
                        directions.put(nodeA, new ArrayList<>(Collections.nCopies(1, new PickUpNode(y, weight, nodeBType))));
                    }
                    break;
                case "Customer":
                    if (directions.containsKey(nodeA)) {
                        directions.get(nodeA).add(new DeliveryNode(y, weight, nodeBType));
                    } else {
                        directions.put(nodeA, new ArrayList<>(Collections.nCopies(1, new DeliveryNode(y, weight, nodeBType))));
                    }
                    break;
            }
        }
        catch (Exception e){
            String errMessage = String.format("Error while connecting two nodes. %s",e.getMessage());
            LOGGER.error(errMessage);
            throw new NodeConnectionException(errMessage);
        }

        try {
            switch (nodeBType) {
                case "Restaurant":
                    if (directions.containsKey(nodeB)) {
                        directions.get(nodeB).add(new PickUpNode(x, weight, nodeAType));
                    } else {
                        directions.put(nodeB, new ArrayList<>(Collections.nCopies(1, new PickUpNode(x, weight, nodeAType))));
                    }
                    break;
                case "Customer":
                    if (directions.containsKey(nodeB)) {
                        directions.get(nodeB).add(new DeliveryNode(x, weight, nodeAType));
                    } else {
                        directions.put(nodeB, new ArrayList<>(Collections.nCopies(1, new DeliveryNode(x, weight, nodeAType))));
                    }
            }
        }
        catch (Exception e){
            String errMessage = String.format("Error while connecting two nodes. %s",e.getMessage());
            LOGGER.error(errMessage);
            throw new NodeConnectionException(errMessage);
        }
    }
}
