package org.application;

public class ParkingNotAllowedException extends Exception {
    private String exception;
    ParkingNotAllowedException(String exception) {
        super(exception);
    }
}
