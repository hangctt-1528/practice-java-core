package com.example.practice2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crockery extends Goods {

    public static final double VAT_RATE = 0.10;
    public static final String TYPE_NAME = "Sanh su";
    public static final int SLOW_QUANTITY_THRESHOLD = 50;
    public static final int SLOW_DAYS_THRESHOLD = 10;

    private String manufacturer;
    private LocalDate arrivalDate;

    public Crockery(String code, String name, int quantity, double unitPrice,
            String manufacturer, LocalDate arrivalDate) {
        super(code, name, quantity, unitPrice);
        this.manufacturer = manufacturer;
        this.arrivalDate = arrivalDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    // Number of days from the arrival date to the reference date
    public long getStorageDays(LocalDate referenceDate) {
        return ChronoUnit.DAYS.between(arrivalDate, referenceDate);
    }

    @Override
    public double getVatRate() {
        return VAT_RATE;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    // Quantity > 50 and stored > 10 days -> slow sale
    @Override
    public String evaluateConsumption(LocalDate referenceDate) {
        if (getQuantity() > SLOW_QUANTITY_THRESHOLD
                && getStorageDays(referenceDate) > SLOW_DAYS_THRESHOLD) {
            return SLOW_SALE;
        }
        return NOT_EVALUATED;
    }

    @Override
    public String toString() {
        return super.toString() + ", Nha SX: " + manufacturer + ", Ngay nhap kho: " + arrivalDate;
    }
}
