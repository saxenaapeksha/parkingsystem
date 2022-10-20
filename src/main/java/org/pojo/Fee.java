package org.pojo;

public class Fee {
    private int price;
    private Interval interval;

    public int getPrice() {
        return price;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }
}
