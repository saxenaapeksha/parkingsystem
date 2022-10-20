package org.strategy;

import org.pojo.Fee;
import org.pojo.Interval;

import java.util.List;

public class Summation implements Strategy {

    public long calculate(List<Fee> fees, long parkedMinutes) {
        long hours = parkedMinutes / 60;
        int charge = 0;
        for (Fee fee : fees) {
            Interval interval = fee.getInterval();
            int max = interval.getMax();
            if (hours < max) {
                if (max == 9999) {
                    long remainingHours = hours - (interval.getMin() - 1);
                    return charge + (remainingHours * fee.getPrice());
                }
                return charge + fee.getPrice();
            } else {
                charge += fee.getPrice();
            }
        }
        return charge;
    }
}
