package org.application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pojo.ParkingReceipt;
import org.pojo.ParkingTicket;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class AirportParkingLotTest {
    AirportParkingLot airportParkingLot;

    @Before
    public void setup() throws FileNotFoundException {
        this.airportParkingLot = new AirportParkingLot();
    }

    @Test
    public void testParkTruck() throws ParkingNotAllowedException {
        ParkingTicket parkingTicket = airportParkingLot.park("truck");
        Assert.assertNotNull(parkingTicket);
        Assert.assertEquals(parkingTicket.getTicketNumber(), "001");
        Assert.assertEquals(701,parkingTicket.getSpot().getSpotNumber());
    }

    @Test
    public void testUnParkForMotorcycle() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = airportParkingLot.park("motorcycle");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        LocalDateTime entryInHours = entry.minusHours(0);
        parkingTicket.getSpot().setEntryTime(entryInHours.minusMinutes(55));
        ParkingReceipt parkingReceipt = airportParkingLot.unPark("motorcycle",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(0,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForMotorcycleII() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = airportParkingLot.park("motorcycle");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        LocalDateTime entryInHours = entry.minusHours(14);
        parkingTicket.getSpot().setEntryTime(entryInHours.minusMinutes(59));
        ParkingReceipt parkingReceipt = airportParkingLot.unPark("motorcycle",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(60,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForMotorcycleIII() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = airportParkingLot.park("motorcycle");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        LocalDateTime day = entry.minusDays(1);
        LocalDateTime entryInHours = day.minusHours(12);
        parkingTicket.getSpot().setEntryTime(entryInHours);
        ParkingReceipt parkingReceipt = airportParkingLot.unPark("motorcycle",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(160,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForCar() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = airportParkingLot.park("car");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        parkingTicket.getSpot().setEntryTime(entry.minusMinutes(50));
        ParkingReceipt parkingReceipt = airportParkingLot.unPark("car",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(60,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForSuv() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = airportParkingLot.park("suv");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        LocalDateTime hours = entry.minusHours(23);
        parkingTicket.getSpot().setEntryTime(hours.minusMinutes(59));
        ParkingReceipt parkingReceipt = airportParkingLot.unPark("suv",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(80,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForCarI() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = airportParkingLot.park("car");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        LocalDateTime day = entry.minusDays(3);
        LocalDateTime hours = day.minusHours(1);
        parkingTicket.getSpot().setEntryTime(hours);
        ParkingReceipt parkingReceipt = airportParkingLot.unPark("car",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(400,parkingReceipt.getFee());
    }
}
