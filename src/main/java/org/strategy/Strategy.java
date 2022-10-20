package org.strategy;

import org.pojo.Fee;

import java.util.List;

public interface Strategy {
    public long calculate(List<Fee> fees, long parkedMinutes);
}
