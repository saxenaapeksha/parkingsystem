package org.application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pojo.ParkingReceipt;
import org.pojo.ParkingTicket;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;

public class StadiumParkingLotTest {
    StadiumParkingLot stadiumParkingLot;

    @Before
    public void setup() throws FileNotFoundException {
        this.stadiumParkingLot = new StadiumParkingLot();
    }

    @Test
    public void testInvalidVehicle() throws ParkingNotAllowedException {
        assertThrows(ParkingNotAllowedException.class, () -> {
            stadiumParkingLot.park("cycle");
        });
    }

    @Test
    public void testParkSUV() throws ParkingNotAllowedException {
        ParkingTicket parkingTicket = stadiumParkingLot.park("suv");
        Assert.assertNotNull(parkingTicket);
        Assert.assertEquals(parkingTicket.getTicketNumber(), "001");
        Assert.assertEquals(parkingTicket.getSpot().getSpotNumber(), 1001);
    }

    @Test
    public void testUnParkForMotorCycle() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = stadiumParkingLot.park("motorcycle");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        parkingTicket.getSpot().setEntryTime(entry.minusMinutes(220));
        ParkingReceipt parkingReceipt = stadiumParkingLot.unPark("motorcycle",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(30,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForMotorCycleII() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = stadiumParkingLot.park("motorcycle");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        LocalDateTime entryInHours = entry.minusHours(14);
        parkingTicket.getSpot().setEntryTime(entryInHours.minusMinutes(59));
        ParkingReceipt parkingReceipt = stadiumParkingLot.unPark("motorcycle",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(390,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForSuv() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = stadiumParkingLot.park("suv");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        LocalDateTime entryInHours = entry.minusHours(11);
        parkingTicket.getSpot().setEntryTime(entryInHours.minusMinutes(30));
        ParkingReceipt parkingReceipt = stadiumParkingLot.unPark("suv",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(180,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForSuvII() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = stadiumParkingLot.park("suv");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        LocalDateTime entryInHours = entry.minusHours(13);
        parkingTicket.getSpot().setEntryTime(entryInHours.minusMinutes(5));
        ParkingReceipt parkingReceipt = stadiumParkingLot.unPark("suv",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(580,parkingReceipt.getFee());
    }
}
