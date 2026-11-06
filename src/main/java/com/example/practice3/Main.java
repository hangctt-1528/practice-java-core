package com.example.practice3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VehicleManager manager = new VehicleManager();
        int choice = -1;

        do {
            printMenu();
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so");
                continue;
            }

            switch (choice) {
                case 1:
                    addVehicleFromInput(sc, manager);
                    break;
                case 2:
                    searchByVehicleNumber(sc, manager);
                    break;
                case 3:
                    searchByOwnerCmnd(sc, manager);
                    break;
                case 4:
                    deleteByManufacturer(sc, manager);
                    break;
                case 5:
                    showTopManufacturers(manager);
                    break;
                case 6:
                    manager.sortByVehicleNumberDesc();
                    manager.displayAll();
                    break;
                case 7:
                    showStatistics(manager);
                    break;
                case 8:
                    manager.displayAll();
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== QUAN LY PHUONG TIEN =====");
        System.out.println("1. Them phuong tien");
        System.out.println("2. Tim phuong tien theo bien so");
        System.out.println("3. Tim phuong tien theo CMND chu xe");
        System.out.println("4. Xoa tat ca phuong tien cua mot hang");
        System.out.println("5. Hang co nhieu phuong tien nhat");
        System.out.println("6. Sap xep theo bien so giam dan");
        System.out.println("7. Thong ke so phuong tien theo loai");
        System.out.println("8. Hien thi danh sach");
        System.out.println("0. Thoat");
        System.out.print("Chon: ");
    }

    // Function 1
    private static void addVehicleFromInput(Scanner sc, VehicleManager manager) {
        try {
            System.out.println("Chon loai: 1. O to  2. Xe may  3. Xe tai");
            System.out.print("Loai: ");
            int type = Integer.parseInt(sc.nextLine());
            if (type < 1 || type > 3) {
                System.out.println("Loai khong hop le");
                return;
            }

            System.out.print("Bien so (5 ky tu): ");
            String number = sc.nextLine();
            if (number.length() != Vehicle.VEHICLE_NUMBER_LENGTH) {
                System.out.println("Bien so phai co dung 5 ky tu");
                return;
            }
            if (manager.existsVehicleNumber(number)) {
                System.out.println("Bien so da ton tai");
                return;
            }

            System.out.print("Hang (Honda, Yamaha, Toyota, Suzuki): ");
            String manufacturer = sc.nextLine();
            if (Vehicle.findManufacturer(manufacturer) == null) {
                System.out.println("Hang chi gom Honda, Yamaha, Toyota, Suzuki");
                return;
            }

            System.out.print("Nam san xuat: ");
            int year = Integer.parseInt(sc.nextLine());
            if (!Vehicle.isValidYear(year, LocalDate.now().getYear())) {
                System.out.println("Nam san xuat phai > 2000 va <= nam hien tai");
                return;
            }

            System.out.print("Mau: ");
            String color = sc.nextLine();

            Owner owner = readOwner(sc, manager);

            Vehicle vehicle = null;
            switch (type) {
                case 1:
                    System.out.print("So cho ngoi: ");
                    int seats = Integer.parseInt(sc.nextLine());
                    System.out.print("Kieu dong co: ");
                    String engineType = sc.nextLine();
                    vehicle = new Car(number, manufacturer, year, color, owner, seats, engineType);
                    break;
                case 2:
                    System.out.print("Cong suat: ");
                    double capacity = Double.parseDouble(sc.nextLine());
                    vehicle = new Motorbike(number, manufacturer, year, color, owner, capacity);
                    break;
                case 3:
                    System.out.print("Trong tai (tan): ");
                    double tonnage = Double.parseDouble(sc.nextLine());
                    vehicle = new Truck(number, manufacturer, year, color, owner, tonnage);
                    break;
                default:
                    System.out.println("Loai khong hop le");
                    return;
            }

            if (manager.addVehicle(vehicle)) {
                System.out.println("Them thanh cong");
            } else {
                System.out.println("Them that bai (trung bien so hoac trung CMND)");
            }
        } catch (IllegalArgumentException e) {
            // Also catches NumberFormatException (a subclass of IllegalArgumentException)
            System.out.println("Du lieu khong hop le: " + e.getMessage());
        }
    }

    // Reuses the existing owner if the CMND is already known, otherwise creates a new one
    private static Owner readOwner(Scanner sc, VehicleManager manager) {
        System.out.print("CMND chu xe (12 chu so): ");
        String cmnd = sc.nextLine();
        if (!Owner.isValidCmnd(cmnd)) {
            // Report early, before asking for the name and email
            throw new IllegalArgumentException("CMND phai co dung 12 chu so");
        }
        Owner existing = manager.findOwnerByCmnd(cmnd);
        if (existing != null) {
            System.out.println("Chu xe da co: " + existing.getFullName());
            return existing;
        }
        System.out.print("Ho ten: ");
        String fullName = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        return new Owner(cmnd, fullName, email);
    }

    // Function 2
    private static void searchByVehicleNumber(Scanner sc, VehicleManager manager) {
        System.out.print("Nhap bien so: ");
        Vehicle v = manager.findByVehicleNumber(sc.nextLine());
        if (v == null) {
            System.out.println("Khong tim thay");
        } else {
            System.out.println(v);
        }
    }

    // Function 3
    private static void searchByOwnerCmnd(Scanner sc, VehicleManager manager) {
        System.out.print("Nhap CMND: ");
        ArrayList<Vehicle> list = manager.findByOwnerCmnd(sc.nextLine());
        if (list.size() == 0) {
            System.out.println("Khong co phuong tien nao cua CMND nay");
            return;
        }
        for (Vehicle v : list) {
            System.out.println(v);
        }
    }

    // Function 4
    private static void deleteByManufacturer(Scanner sc, VehicleManager manager) {
        System.out.print("Nhap hang: ");
        String name = sc.nextLine();
        if (Vehicle.findManufacturer(name) == null) {
            System.out.println("Hang chi gom Honda, Yamaha, Toyota, Suzuki");
            return;
        }
        int n = manager.deleteByManufacturer(name);
        System.out.println("Da xoa " + n + " phuong tien");
    }

    // Function 5
    private static void showTopManufacturers(VehicleManager manager) {
        ArrayList<String> top = manager.getTopManufacturers();
        if (top.size() == 0) {
            System.out.println("Chua co phuong tien nao");
            return;
        }
        for (String m : top) {
            System.out.println(m + ": " + manager.countByManufacturer(m) + " xe");
        }
    }

    // Function 7
    private static void showStatistics(VehicleManager manager) {
        System.out.println("So phuong tien theo loai:");
        System.out.println(Car.TYPE_NAME + ": " + manager.countByType(Car.TYPE_NAME));
        System.out.println(Motorbike.TYPE_NAME + ": " + manager.countByType(Motorbike.TYPE_NAME));
        System.out.println(Truck.TYPE_NAME + ": " + manager.countByType(Truck.TYPE_NAME));
    }
}
