package com.example.practice2;

// Manages the goods list (DSHH) with a fixed-size array and a counter
public class GoodsManager {

    private Goods[] goodsList;
    private int count;

    public GoodsManager(int capacity) {
        goodsList = new Goods[capacity];
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public boolean isFull() {
        return count == goodsList.length;
    }

    public boolean existsCode(String code) {
        for (int i = 0; i < count; i++) {
            if (goodsList[i].getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    // Adds only if the list is not full and the code is not a duplicate
    public boolean addGoods(Goods goods) {
        if (isFull()) {
            return false;
        }
        if (existsCode(goods.getCode())) {
            return false;
        }
        goodsList[count] = goods;
        count++;
        return true;
    }

    public int getTotalQuantityByType(String typeName) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (goodsList[i].getTypeName().equals(typeName)) {
                total += goodsList[i].getQuantity();
            }
        }
        return total;
    }

    public void displayAll() {
        if (count == 0) {
            System.out.println("Danh sach trong");
            return;
        }
        // Loop to count, not goodsList.length: cells after count are null
        for (int i = 0; i < count; i++) {
            Goods goods = goodsList[i];
            System.out.println(goods);
            System.out.println("   VAT: " + goods.getVatAmount() + " | Danh gia: "
                    + goods.evaluateConsumption());
        }
    }
}
