package com.example.practice3;

import java.time.LocalDate;

public abstract class Vehicle {

    // Allowed manufacturers (used instead of an enum)
    public static final String[] MANUFACTURERS = {"Honda", "Yamaha", "Toyota", "Suzuki"};
    public static final int VEHICLE_NUMBER_LENGTH = 5;
    public static final int MIN_YEAR_EXCLUSIVE = 2000;

    private final String vehicleNumber; // no setter: the number must stay unique
    private String manufacturer;
    private int yearOfManufacture;
    private String color;
    private Owner owner;

    public Vehicle(String vehicleNumber, String manufacturer, int yearOfManufacture, String color,
            Owner owner) {
        if (vehicleNumber.length() != VEHICLE_NUMBER_LENGTH) {
            throw new IllegalArgumentException("Bien so phai co dung 5 ky tu");
        }
        String standardName = findManufacturer(manufacturer);
        if (standardName == null) {
            throw new IllegalArgumentException("Hang chi gom Honda, Yamaha, Toyota, Suzuki");
        }
        if (!isValidYear(yearOfManufacture, LocalDate.now().getYear())) {
            throw new IllegalArgumentException("Nam san xuat phai > 2000 va <= nam hien tai");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Phuong tien phai co chu so huu");
        }
        this.vehicleNumber = vehicleNumber;
        this.manufacturer = standardName;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.owner = owner;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        String standardName = findManufacturer(manufacturer);
        if (standardName == null) {
            throw new IllegalArgumentException("Hang chi gom Honda, Yamaha, Toyota, Suzuki");
        }
        this.manufacturer = standardName;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        if (!isValidYear(yearOfManufacture, LocalDate.now().getYear())) {
            throw new IllegalArgumentException("Nam san xuat phai > 2000 va <= nam hien tai");
        }
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Owner getOwner() {
        return owner;
    }

    // Each vehicle type has its own type name
    public abstract String getTypeName();

    // Returns the standard name ("honda" -> "Honda"), or null if not allowed
    public static String findManufacturer(String name) {
        for (int i = 0; i < MANUFACTURERS.length; i++) {
            if (MANUFACTURERS[i].equalsIgnoreCase(name)) {
                return MANUFACTURERS[i];
            }
        }
        return null;
    }

    // currentYear is a parameter so tests can use a fixed year
    public static boolean isValidYear(int year, int currentYear) {
        return year > MIN_YEAR_EXCLUSIVE && year <= currentYear;
    }

    @Override
    public String toString() {
        return "[" + getTypeName() + "] Bien so: " + vehicleNumber + ", Hang: " + manufacturer
                + ", Nam SX: " + yearOfManufacture + ", Mau: " + color
                + ", Chu xe: " + owner.getFullName() + " (CMND " + owner.getCmnd() + ")";
    }
}
