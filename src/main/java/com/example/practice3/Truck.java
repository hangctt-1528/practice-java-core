package com.example.practice3;

public class Truck extends Vehicle {

    public static final String TYPE_NAME = "Xe tai";

    private double tonnage;

    public Truck(String vehicleNumber, String manufacturer, int yearOfManufacture, String color,
            Owner owner, double tonnage) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
        this.tonnage = tonnage;
    }

    public double getTonnage() {
        return tonnage;
    }

    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    @Override
    public String toString() {
        return super.toString() + ", Trong tai: " + tonnage + " tan";
    }
}
