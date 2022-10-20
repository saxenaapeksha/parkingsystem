package org.application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pojo.ParkingReceipt;
import org.pojo.ParkingTicket;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class MallParkingLotTest {
    MallParkingLot mall;

    @Before
    public void setup() throws FileNotFoundException {
        this.mall = new MallParkingLot();
    }

    @Test
    public void testParkScooter() throws FileNotFoundException, ParkingNotAllowedException {
        ParkingTicket parkingTicket = getParkingTicketForVehicleType("scooter");
        Assert.assertNotNull(parkingTicket);
        Assert.assertEquals(parkingTicket.getTicketNumber(), "001");
        Assert.assertEquals(parkingTicket.getSpot().getSpotNumber(), 1);
        Assert.assertNotNull(parkingTicket.getSpot().getEntryTime());
    }

    @Test
    public void testParkCar() throws FileNotFoundException, ParkingNotAllowedException {
        getParkingTicketForVehicleType("scooter");
        getParkingTicketForVehicleType("car");
        ParkingTicket parkingTicket = getParkingTicketForVehicleType("car");
        Assert.assertNotNull(parkingTicket);
        Assert.assertEquals(parkingTicket.getTicketNumber(), "003");
        Assert.assertEquals(parkingTicket.getSpot().getSpotNumber(), 3);
        Assert.assertNotNull(parkingTicket.getSpot().getEntryTime());
    }

    @Test
    public void testParkBus() throws FileNotFoundException, ParkingNotAllowedException {
        ParkingTicket parkingTicket = getParkingTicketForVehicleType("bus");
        Assert.assertNotNull(parkingTicket);
        Assert.assertEquals(parkingTicket.getTicketNumber(), "001");
        Assert.assertEquals(parkingTicket.getSpot().getSpotNumber(), 4);
        Assert.assertNotNull(parkingTicket.getSpot().getEntryTime());
    }

    @Test
    public void testUnPark() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = getParkingTicketForVehicleType("scooter");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        parkingTicket.getSpot().setEntryTime(entry.minusHours(1));
        ParkingReceipt parkingReceipt = mall.unPark("scooter",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals("R-001",parkingReceipt.getReceiptNumber());
        Assert.assertEquals(10,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForMotorCycle() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = getParkingTicketForVehicleType("motorcycle");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        parkingTicket.getSpot().setEntryTime(entry.minusMinutes(210));
        ParkingReceipt parkingReceipt = mall.unPark("motorcycle",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(40,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForCar() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = getParkingTicketForVehicleType("car");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        parkingTicket.getSpot().setEntryTime(entry.minusMinutes(361));
        ParkingReceipt parkingReceipt = mall.unPark("car",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(140,parkingReceipt.getFee());
    }

    @Test
    public void testUnParkForTruck() throws ParkingNotAllowedException, FileNotFoundException {
        ParkingTicket parkingTicket = getParkingTicketForVehicleType("truck");
        LocalDateTime entry = parkingTicket.getSpot().getEntryTime();
        parkingTicket.getSpot().setEntryTime(entry.minusMinutes(119));
        ParkingReceipt parkingReceipt = mall.unPark("truck",parkingTicket.getTicketNumber());
        Assert.assertNotNull(parkingReceipt);
        Assert.assertEquals(100,parkingReceipt.getFee());
    }

    private ParkingTicket getParkingTicketForVehicleType(String vehicle) throws FileNotFoundException, ParkingNotAllowedException {
        ParkingTicket parkingTicket = mall.park(vehicle);
        return parkingTicket;
    }
}
