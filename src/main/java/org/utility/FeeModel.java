package org.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pojo.Fee;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeeModel {
    private String vehicleType;
    private List<Fee> fees;
    static String filename = "src/main/resources/fee.yaml";

    public static Map<String, List<FeeModel>> read() {
        Map<String, List<FeeModel>> feeMap = null;
        try {
            InputStream inputStream = new FileInputStream(new File(filename));
            Yaml yaml = new Yaml();
            List<Object> data = (List<Object>) yaml.load(inputStream);
            if (data == null)
                return null;
            feeMap = new HashMap<>();
            for (Object map : data) {
                Map<String, Object> newMap = (Map<String, Object>) map;
                String parkingLotType = (String) newMap.get("name");
                List<HashMap<String,Object>> feesStructure = (List<HashMap<String,Object>>) newMap.get("feesStructure");
                List<FeeModel> feeModelList = new ArrayList<>();
                for (HashMap<String,Object> feeStructureMap: feesStructure) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    FeeModel feeModel = objectMapper.convertValue(feeStructureMap,FeeModel.class);
                    feeModelList.add(feeModel);
                }
                feeMap.put(parkingLotType, feeModelList);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return feeMap;
    }


    public String getVehicleType() {
        return vehicleType;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }
}
