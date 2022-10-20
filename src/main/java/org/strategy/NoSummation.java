package org.strategy;

import org.pojo.Fee;
import java.util.List;

public class NoSummation implements Strategy {

    public long calculate(List<Fee> fees, long parkedMinutes) {
        long charge = 0;
        int day = 0;
        long hours = parkedMinutes / 60;

        for (int i = fees.size() - 1; i >= 0; i--) {
            if ("day".equals(fees.get(i).getInterval().getUnit())) {
                day = (int) (hours / 24);
                hours = (int) (hours % 24);
                if (hours > 0 && day > 0) {
                    day++;
                    hours = 0;
                }
                if (day >= fees.get(i).getInterval().getMin()) {
                    charge = (long) day * fees.get(i).getPrice();
                    break;
                }
            } else {
                if (hours >= fees.get(i).getInterval().getMin()) {
                    charge += fees.get(i).getPrice();
                    break;
                }
            }
        }
        return charge;
    }
}
