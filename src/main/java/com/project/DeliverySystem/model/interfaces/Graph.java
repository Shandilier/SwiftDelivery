package com.project.DeliverySystem.model.interfaces;

import com.project.DeliverySystem.Exception.NodeConnectionException;

public interface Graph {

    public void addEdge(int x, int y, int weight, String nodeAType, String nodeBType, int foodPreparationTime) throws NodeConnectionException;
}
