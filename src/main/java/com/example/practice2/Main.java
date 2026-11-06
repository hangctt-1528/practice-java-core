package com.example.practice2;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static final int DEFAULT_CAPACITY = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GoodsManager manager = new GoodsManager(DEFAULT_CAPACITY);
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
                    addGoodsFromInput(sc, manager);
                    break;
                case 2:
                    manager.displayAll();
                    break;
                case 3:
                    showStatistics(manager);
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
        System.out.println("===== QUAN LY HANG TON KHO =====");
        System.out.println("1. Them hang hoa");
        System.out.println("2. Hien thi danh sach (kem VAT va danh gia)");
        System.out.println("3. Thong ke ton kho theo loai");
        System.out.println("0. Thoat");
        System.out.print("Chon: ");
    }

    private static void addGoodsFromInput(Scanner sc, GoodsManager manager) {
        if (manager.isFull()) {
            System.out.println("Danh sach da day, khong the them");
            return;
        }

        try {
            System.out.println("Chon loai hang: 1. Thuc pham  2. Dien may  3. Sanh su");
            System.out.print("Loai: ");
            int type = Integer.parseInt(sc.nextLine());
            if (type < 1 || type > 3) {
                System.out.println("Loai hang khong hop le");
                return;
            }

            System.out.print("Ma hang: ");
            String code = sc.nextLine();
            if (manager.existsCode(code)) {
                System.out.println("Ma hang da ton tai");
                return;
            }

            System.out.print("Ten hang: ");
            String name = sc.nextLine();
            System.out.print("So luong ton: ");
            int quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Don gia: ");
            double unitPrice = Double.parseDouble(sc.nextLine());

            Goods goods = null;
            switch (type) {
                case 1:
                    goods = readFood(sc, code, name, quantity, unitPrice);
                    break;
                case 2:
                    goods = readElectronics(sc, code, name, quantity, unitPrice);
                    break;
                case 3:
                    goods = readCrockery(sc, code, name, quantity, unitPrice);
                    break;
                default:
                    System.out.println("Loai hang khong hop le");
                    return;
            }

            if (manager.addGoods(goods)) {
                System.out.println("Them thanh cong");
            } else {
                System.out.println("Them that bai (trung ma hoac danh sach day)");
            }
        } catch (DateTimeParseException e) {
            // Specific exception first: a date not in yyyy-MM-dd format
            System.out.println("Ngay phai co dang yyyy-MM-dd");
        } catch (IllegalArgumentException e) {
            // Also catches NumberFormatException (a subclass of IllegalArgumentException)
            System.out.println("Du lieu khong hop le: " + e.getMessage());
        }
    }

    private static Food readFood(Scanner sc, String code, String name, int quantity,
            double unitPrice) {
        System.out.print("Ngay san xuat (yyyy-MM-dd): ");
        LocalDate manufactureDate = LocalDate.parse(sc.nextLine());
        System.out.print("Ngay het han (yyyy-MM-dd): ");
        LocalDate expirationDate = LocalDate.parse(sc.nextLine());
        System.out.print("Nha cung cap: ");
        String supplier = sc.nextLine();
        return new Food(code, name, quantity, unitPrice, manufactureDate, expirationDate, supplier);
    }

    private static Electronics readElectronics(Scanner sc, String code, String name, int quantity,
            double unitPrice) {
        System.out.print("Bao hanh (thang): ");
        int warrantyMonths = Integer.parseInt(sc.nextLine());
        System.out.print("Cong suat (KW): ");
        double capacityKw = Double.parseDouble(sc.nextLine());
        return new Electronics(code, name, quantity, unitPrice, warrantyMonths, capacityKw);
    }

    private static Crockery readCrockery(Scanner sc, String code, String name, int quantity,
            double unitPrice) {
        System.out.print("Nha san xuat: ");
        String manufacturer = sc.nextLine();
        System.out.print("Ngay nhap kho (yyyy-MM-dd): ");
        LocalDate arrivalDate = LocalDate.parse(sc.nextLine());
        return new Crockery(code, name, quantity, unitPrice, manufacturer, arrivalDate);
    }

    private static void showStatistics(GoodsManager manager) {
        System.out.println("Tong so luong ton theo loai:");
        System.out.println(Food.TYPE_NAME + ": " + manager.getTotalQuantityByType(Food.TYPE_NAME));
        System.out.println(Electronics.TYPE_NAME + ": "
                + manager.getTotalQuantityByType(Electronics.TYPE_NAME));
        System.out.println(Crockery.TYPE_NAME + ": "
                + manager.getTotalQuantityByType(Crockery.TYPE_NAME));
    }
}
