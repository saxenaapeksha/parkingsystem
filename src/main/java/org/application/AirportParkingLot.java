package org.application;

import org.utility.FeeModel;
import org.strategy.NoSummation;
import org.strategy.Strategy;
import org.utility.YamlUtility;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class AirportParkingLot extends ParkingLot {
    public AirportParkingLot() throws FileNotFoundException {
        super();
    }

    @Override
    public Map<String, Integer> loadSpotData() throws FileNotFoundException {
        return YamlUtility.loadYaml().get("airport");
    }

    @Override
    public List<FeeModel> parkingLotFeeModel() {
        return FeeModel.read().get("airport");
    }

    @Override
    public Strategy getStrategy() {
        return new NoSummation();
    }
}
