package org.strategy;

import org.pojo.Fee;
import java.util.List;

public class FlatRate implements Strategy {

    public long calculate(List<Fee> fees, long parkedMinutes) {
        Fee fee = fees.get(0);
        long hours = parkedMinutes / 60;
        long minutes = parkedMinutes % 60;
        if (minutes > 0) {
            hours++;
        }
        return hours * fee.getPrice();
    }
}
