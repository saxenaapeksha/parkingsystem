package org.application;

import org.utility.FeeModel;
import org.strategy.FlatRate;
import org.strategy.Strategy;
import org.utility.YamlUtility;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class MallParkingLot extends ParkingLot {
    public MallParkingLot() throws FileNotFoundException {
        super();
    }

    @Override
    public Map<String, Integer> loadSpotData() throws FileNotFoundException {
        return YamlUtility.loadYaml().get("mall");
    }

    @Override
    public List<FeeModel> parkingLotFeeModel() {
        return FeeModel.read().get("mall");
    }

    @Override
    public Strategy getStrategy() {
        return new FlatRate();
    }
}
