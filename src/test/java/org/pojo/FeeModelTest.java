package org.pojo;

import org.junit.Assert;
import org.junit.Test;
import org.utility.FeeModel;

import java.util.List;
import java.util.Map;

public class FeeModelTest {

    @Test
    public void testReadFeeModel() {
        FeeModel feeModel = new FeeModel();
        Map<String, List<FeeModel>> response = FeeModel.read();
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.get("Mall"));
        List<FeeModel> feeStructure = response.get("Mall");
        Assert.assertEquals(feeStructure.size(), 3);
        Assert.assertEquals(feeStructure.get(0).getFees().get(0).getPrice(), 10);
    }
}
