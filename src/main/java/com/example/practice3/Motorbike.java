package com.example.practice3;

public class Motorbike extends Vehicle {

    public static final String TYPE_NAME = "Xe may";

    private double capacity;

    public Motorbike(String vehicleNumber, String manufacturer, int yearOfManufacture, String color,
            Owner owner, double capacity) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    @Override
    public String toString() {
        return super.toString() + ", Cong suat: " + capacity;
    }
}
