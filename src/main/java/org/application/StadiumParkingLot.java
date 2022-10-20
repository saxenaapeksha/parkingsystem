package org.application;

import org.utility.FeeModel;
import org.strategy.Strategy;
import org.strategy.Summation;
import org.utility.YamlUtility;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class StadiumParkingLot extends ParkingLot {

    public StadiumParkingLot() throws FileNotFoundException {
        super();
    }

    @Override
    public Map<String, Integer> loadSpotData() throws FileNotFoundException {
        return YamlUtility.loadYaml().get("stadium");
    }

    @Override
    public List<FeeModel> parkingLotFeeModel() {
        return FeeModel.read().get("stadium");
    }

    @Override
    public Strategy getStrategy() {
        return new Summation();
    }
}
