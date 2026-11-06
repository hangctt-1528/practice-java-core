package com.example.practice2;

import java.time.LocalDate;

public class Food extends Goods {

    public static final double VAT_RATE = 0.05;
    public static final String TYPE_NAME = "Thuc pham";

    private LocalDate manufactureDate;
    private LocalDate expirationDate;
    private String supplier;

    public Food(String code, String name, int quantity, double unitPrice,
            LocalDate manufactureDate, LocalDate expirationDate, String supplier) {
        super(code, name, quantity, unitPrice);
        if (expirationDate.isBefore(manufactureDate)) {
            throw new IllegalArgumentException("Ngay het han phai sau hoac bang ngay san xuat");
        }
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        if (expirationDate.isBefore(manufactureDate)) {
            throw new IllegalArgumentException("Ngay het han phai sau hoac bang ngay san xuat");
        }
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isBefore(manufactureDate)) {
            throw new IllegalArgumentException("Ngay het han phai sau hoac bang ngay san xuat");
        }
        this.expirationDate = expirationDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    // On the expiration date itself the food is not expired yet
    public boolean isExpired(LocalDate referenceDate) {
        return expirationDate.isBefore(referenceDate);
    }

    @Override
    public double getVatRate() {
        return VAT_RATE;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    // Still in stock and expired -> hard to sell
    @Override
    public String evaluateConsumption(LocalDate referenceDate) {
        if (getQuantity() > 0 && isExpired(referenceDate)) {
            return HARD_TO_SELL;
        }
        return NOT_EVALUATED;
    }

    @Override
    public String toString() {
        return super.toString() + ", NSX: " + manufactureDate + ", HSD: " + expirationDate
                + ", NCC: " + supplier;
    }
}
