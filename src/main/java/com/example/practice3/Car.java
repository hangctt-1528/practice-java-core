package com.example.practice3;

public class Car extends Vehicle {

    public static final String TYPE_NAME = "O to";

    private int numberOfSeats;
    private String engineType;

    public Car(String vehicleNumber, String manufacturer, int yearOfManufacture, String color,
            Owner owner, int numberOfSeats, String engineType) {
        super(vehicleNumber, manufacturer, yearOfManufacture, color, owner);
        this.numberOfSeats = numberOfSeats;
        this.engineType = engineType;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String getTypeName() {
        return TYPE_NAME;
    }

    @Override
    public String toString() {
        return super.toString() + ", So cho: " + numberOfSeats + ", Dong co: " + engineType;
    }
}
