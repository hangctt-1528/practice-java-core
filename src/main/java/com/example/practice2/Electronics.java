package com.example.practice2;

import java.time.LocalDate;

public class Electronics extends Goods {

    public static final double VAT_RATE = 0.10;
    public static final String TYPE_NAME = "Dien may";
    public static final int SOLD_THRESHOLD = 3;

    private int warrantyMonths;
    private double capacityKw;

    public Electronics(String code, String name, int quantity, double unitPrice,
            int warrantyMonths, double capacityKw) {
        super(code, name, quantity, unitPrice);
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Thoi gian bao hanh phai >= 0");
        }
        if (capacityKw < 0) {
            throw new IllegalArgumentException("Cong suat phai >= 0");
        }
        this.warrantyMonths = warrantyMonths;
        this.capacityKw = capacityKw;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Thoi gian bao hanh phai >= 0");
        }
        this.warrantyMonths = warrantyMonths;
    }

    public double getCapacityKw() {
        return capacityKw;
    }

    public void setCapacityKw(double capacityKw) {
        if (capacityKw < 0) {
            throw new IllegalArgumentException("Cong suat phai >= 0");
        }
        this.capacityKw = capacityKw;
    }

    @Override
    public double getVatRate() {
        return VAT_RATE;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    // Quantity < 3 -> sold. The date is not used for electronics,
    // but the parameter must stay to override the parent method.
    @Override
    public String evaluateConsumption(LocalDate referenceDate) {
        if (getQuantity() < SOLD_THRESHOLD) {
            return SOLD;
        }
        return NOT_EVALUATED;
    }

    @Override
    public String toString() {
        return super.toString() + ", Bao hanh: " + warrantyMonths + " thang, Cong suat: "
                + capacityKw + " KW";
    }
}
