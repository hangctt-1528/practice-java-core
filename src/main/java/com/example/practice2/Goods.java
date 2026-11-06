package com.example.practice2;

import java.time.LocalDate;

public abstract class Goods {

    // Consumption evaluation results
    public static final String SOLD = "Ban duoc";
    public static final String HARD_TO_SELL = "Kho ban";
    public static final String SLOW_SALE = "Ban cham";
    public static final String NOT_EVALUATED = "Khong danh gia";

    private final String code; // no setter: the code must stay unique in the list
    private String name;
    private int quantity;
    private double unitPrice;

    public Goods(String code, String name, int quantity, double unitPrice) {
        if (quantity < 0) {
            throw new IllegalArgumentException("So luong ton phai >= 0");
        }
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("So luong ton phai >= 0");
        }
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Each type of goods has its own VAT rate
    public abstract double getVatRate();

    // Each type of goods has its own type name
    public abstract String getTypeName();

    // Each type of goods has its own evaluation rule
    public abstract String evaluateConsumption(LocalDate referenceDate);

    // VAT = quantity * unit price * VAT rate (getVatRate() runs the subclass's version)
    public double getVatAmount() {
        return quantity * unitPrice * getVatRate();
    }

    // Overload: evaluate with today's date
    public String evaluateConsumption() {
        return evaluateConsumption(LocalDate.now());
    }

    @Override
    public String toString() {
        return "[" + getTypeName() + "] Ma: " + code + ", Ten: " + name
                + ", SL ton: " + quantity + ", Don gia: " + unitPrice;
    }
}
