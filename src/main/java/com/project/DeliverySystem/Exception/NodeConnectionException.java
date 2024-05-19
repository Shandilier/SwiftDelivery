package com.project.DeliverySystem.Exception;

public class NodeConnectionException extends Exception {

    private String message;

    public NodeConnectionException(String message) {
        super(message);
    }
}
