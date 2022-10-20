package org.application;

import org.pojo.*;
import org.strategy.Strategy;
import org.utility.FeeModel;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public abstract class ParkingLot {

    public static final Map<String, String> vehicleMap = new HashMap<>();

    static {
        vehicleMap.put("motorcycle", "twoWheeler");
        vehicleMap.put("scooter", "twoWheeler");
        vehicleMap.put("suv", "lightWeightFourWheeler");
        vehicleMap.put("car", "lightWeightFourWheeler");
        vehicleMap.put("bus", "heavyWeightFourWheeler");
        vehicleMap.put("truck", "heavyWeightFourWheeler");
    }

    private final Map<String, Spot> ticketsMap;
    private final List<String> receipts;
    private final Map<String, Queue<Spot>> spotRegister;
    private final List<FeeModel> feeModels;
    private int spotCounter = 0;


    public ParkingLot() throws FileNotFoundException {
        spotRegister = new HashMap<>();
        receipts = new ArrayList<>();
        ticketsMap = new HashMap<>();
        Map<String, Integer> parkingSpotMap = loadSpotData();
        if (parkingSpotMap.containsKey("twoWheelerSpots"))
            spotRegister.put("twoWheeler", buildSpot(parkingSpotMap.get("twoWheelerSpots")));
        if (parkingSpotMap.containsKey("lightWeightFourWheelerSpots"))
            spotRegister.put("lightWeightFourWheeler", buildSpot(parkingSpotMap.get("lightWeightFourWheelerSpots")));
        if (parkingSpotMap.containsKey("heavyWeightFourWheelerSpots"))
            spotRegister.put("heavyWeightFourWheeler", buildSpot(parkingSpotMap.get("heavyWeightFourWheelerSpots")));
        this.feeModels = parkingLotFeeModel();
    }

    public abstract List<FeeModel> parkingLotFeeModel();

    public abstract Strategy getStrategy();

    public abstract Map<String, Integer> loadSpotData() throws FileNotFoundException;

    public ParkingTicket park(String vehicle) throws ParkingNotAllowedException {
        ParkingTicket parkingTicket = new ParkingTicket();
        String vehicleType = vehicleMap.get(vehicle);
        if (vehicleType == null)
            throw new ParkingNotAllowedException("vehicle is not allowed");
        Spot spot = allocateSpotFromRegistry(vehicleType);
        spot.setEntryTime(LocalDateTime.now());
        parkingTicket.setSpot(spot);
        parkingTicket.setTicketNumber(generateTicketNumber());
        ticketsMap.put(parkingTicket.getTicketNumber(), spot);
        return parkingTicket;
    }

    public ParkingReceipt unPark(String vehicle, String ticketNumber) {
        String vehicleType = vehicleMap.get(vehicle);
        Spot spot = getSpot(ticketNumber);
        LocalDateTime entry = spot.getEntryTime();
        LocalDateTime exit = LocalDateTime.now();
        long fee = calculateFee(entry, exit, vehicleType);
        returnSpotToRegistry(vehicleType, spot);
        return generateParkingReceipt(entry, exit, fee);
    }

    private Spot allocateSpotFromRegistry(String vehicleType) throws ParkingNotAllowedException {
        Queue<Spot> spots = spotRegister.get(vehicleType);
        if (spots == null)
            throw new ParkingNotAllowedException("Parking for this vehicle is not allowed");
        if (spots.size() == 0)
            throw new ParkingNotAllowedException("No Space Available");
        return spots.poll();
    }

    private ParkingReceipt generateParkingReceipt(LocalDateTime entry, LocalDateTime exit, long fee) {
        ParkingReceipt parkingReceipt = new ParkingReceipt();
        parkingReceipt.setEntryTime(entry);
        parkingReceipt.setReceiptNumber(generateReceiptNumber());
        parkingReceipt.setExit(exit);
        parkingReceipt.setFee(fee);
        return parkingReceipt;
    }

    private Spot getSpot(String ticketNumber) {
        return ticketsMap.get(ticketNumber);
    }

    private void returnSpotToRegistry(String vehicleType, Spot spot) {
        Queue<Spot> spots = spotRegister.get(vehicleType);
        Spot newSpotObj = new Spot();
        newSpotObj.setSpotNumber(spot.getSpotNumber());
        spots.add(newSpotObj);
    }

    private long calculateFee(LocalDateTime entry, LocalDateTime exit, String vehicleType) {
        List<Fee> fees = null;
        long parkedDurationInMinutes = ChronoUnit.MINUTES.between(entry, exit);
        for (FeeModel feeModel : feeModels) {
            if (feeModel.getVehicleType().equals(vehicleType)) {
                fees = feeModel.getFees();
                break;
            }
        }
        return getStrategy().calculate(fees, parkedDurationInMinutes);
    }

    private String generateTicketNumber() {
        return String.format("%03d", ticketsMap.size() + 1);
    }

    private String generateReceiptNumber() {
        return "R-" + String.format("%03d", receipts.size() + 1);
    }

    private Queue<Spot> buildSpot(int spots) {
        Spot customSpot = null;
        Queue<Spot> spotQueue = new LinkedList<>();
        for (int foo = 0; foo < spots; foo++) {
            customSpot = new Spot();
            customSpot.setSpotNumber(++spotCounter);
            spotQueue.add(customSpot);
        }
        return spotQueue;
    }

}
