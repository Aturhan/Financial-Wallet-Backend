package com.currency.Backend.model.constants;

public enum SpendType {
    EXPENSE("EXPENSE"),
    INCOME("INCOME");

    private final String value;

    SpendType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
