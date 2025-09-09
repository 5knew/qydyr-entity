package com.example.qydyr.model.enums;

public enum Status {
    PAID ("PAID"),
    PAYMENT_EXPECTED ("PAYMENT EXPECTED"),
    PAYMENT_IS_OVERDUE ("PAYMENT IS OVERDUE"),
    REFUND ("REFUND"),
    ACTIVE("ACTIVE"),
    PURCHASED("PURCHASED");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
