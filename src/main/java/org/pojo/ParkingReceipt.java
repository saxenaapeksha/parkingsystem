package org.pojo;

import java.time.LocalDateTime;
public class ParkingReceipt {
    private String receiptNumber;
    private long fee;
    private LocalDateTime entry;
    private LocalDateTime exit;

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public LocalDateTime getEntryTime() {
        return entry;
    }

    public void setEntryTime(LocalDateTime entry) {
        this.entry = entry;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public void setExit(LocalDateTime exit) {
        this.exit = exit;
    }
}
