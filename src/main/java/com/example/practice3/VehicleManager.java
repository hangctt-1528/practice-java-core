package com.example.practice3;

import java.util.ArrayList;

public class VehicleManager {

    private ArrayList<Vehicle> vehicles;

    public VehicleManager() {
        vehicles = new ArrayList<>();
    }

    public int getCount() {
        return vehicles.size();
    }

    public Vehicle getVehicleAt(int index) {
        return vehicles.get(index);
    }

    public boolean existsVehicleNumber(String vehicleNumber) {
        return findByVehicleNumber(vehicleNumber) != null;
    }

    // Owners are found through the vehicles they own
    public Owner findOwnerByCmnd(String cmnd) {
        for (Vehicle v : vehicles) {
            if (v.getOwner().getCmnd().equals(cmnd)) {
                return v.getOwner();
            }
        }
        return null;
    }

    // Function 1: add only if the number is new and the CMND is not used by another owner
    public boolean addVehicle(Vehicle vehicle) {
        if (existsVehicleNumber(vehicle.getVehicleNumber())) {
            return false;
        }
        Owner existing = findOwnerByCmnd(vehicle.getOwner().getCmnd());
        // != compares references: one CMND must belong to one single Owner object
        if (existing != null && existing != vehicle.getOwner()) {
            return false;
        }
        vehicles.add(vehicle);
        return true;
    }

    // Function 2
    public Vehicle findByVehicleNumber(String vehicleNumber) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumber().equalsIgnoreCase(vehicleNumber)) {
                return v;
            }
        }
        return null;
    }

    // Function 3: all vehicles of the owner (may be empty)
    public ArrayList<Vehicle> findByOwnerCmnd(String cmnd) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getOwner().getCmnd().equals(cmnd)) {
                result.add(v);
            }
        }
        return result;
    }

    // Function 4: returns the number of deleted vehicles
    public int deleteByManufacturer(String manufacturer) {
        int deleted = 0;
        // Loop backwards: removing an element shifts only the elements after it
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            if (vehicles.get(i).getManufacturer().equalsIgnoreCase(manufacturer)) {
                vehicles.remove(i);
                deleted++;
            }
        }
        return deleted;
    }

    public int countByManufacturer(String manufacturer) {
        int count = 0;
        for (Vehicle v : vehicles) {
            if (v.getManufacturer().equalsIgnoreCase(manufacturer)) {
                count++;
            }
        }
        return count;
    }

    // Function 5: every manufacturer with the highest count (empty if there are no vehicles)
    public ArrayList<String> getTopManufacturers() {
        int max = 0;
        for (String m : Vehicle.MANUFACTURERS) {
            int c = countByManufacturer(m);
            if (c > max) {
                max = c;
            }
        }

        ArrayList<String> result = new ArrayList<>();
        if (max == 0) {
            return result;
        }
        for (String m : Vehicle.MANUFACTURERS) {
            if (countByManufacturer(m) == max) {
                result.add(m);
            }
        }
        return result;
    }

    // Function 6: bubble sort by vehicle number, descending
    public void sortByVehicleNumberDesc() {
        int n = vehicles.size();
        Vehicle[] arr = new Vehicle[n];
        for (int i = 0; i < n; i++) {
            arr[i] = vehicles.get(i);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                // Earlier one is smaller than the next one -> swap to get descending order
                if (arr[j].getVehicleNumber().compareTo(arr[j + 1].getVehicleNumber()) < 0) {
                    Vehicle temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        vehicles.clear();
        for (Vehicle v : arr) {
            vehicles.add(v);
        }
    }

    // Function 7
    public int countByType(String typeName) {
        int count = 0;
        for (Vehicle v : vehicles) {
            if (v.getTypeName().equals(typeName)) {
                count++;
            }
        }
        return count;
    }

    public void displayAll() {
        if (vehicles.size() == 0) {
            System.out.println("Danh sach trong");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }
}
