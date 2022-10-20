package org.application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pojo.ParkingReceipt;
import org.pojo.ParkingTicket;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;

public class SmallParkingLotTest {
    SmallParkingLot smallParkingLot;

    @Before
    public void setup() throws FileNotFoundException {
        this.smallParkingLot = new SmallParkingLot();
    }

    @Test
    public void testInvalidParking() throws ParkingNotAllowedException {
        assertThrows(ParkingNotAllowedException.class, () -> {
            smallParkingLot.park("bus");
        });
    }

    @Test
    public void testParkScooter() throws ParkingNotAllowedException {
        ParkingTicket parkingTicket = smallParkingLot.park("scooter");
        Assert.assertNotNull(parkingTicket);
        Assert.assertEquals(parkingTicket.getTicketNumber(), "001");
        Assert.assertEquals(parkingTicket.getSpot().getSpotNumber(), 1);
    }

    @Test
    public void testUnParkForScooter() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = smallParkingLot.park("scooter");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        parkingTicket.getSpot().setEntryTime(entry.minusMinutes(60));
        ParkingReceipt parkingReceipt = smallParkingLot.unPark("scooter",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(10,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForMotorCycle() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = smallParkingLot.park("motorcycle");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        parkingTicket.getSpot().setEntryTime(entry.minusMinutes(224));
        ParkingReceipt parkingReceipt = smallParkingLot.unPark("motorcycle",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(40,parkingReceipt.getFee());
    }
}
