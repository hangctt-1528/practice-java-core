package com.example.practice1;

public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    @Override
    public void display() {
        System.out.printf("Square: side = %.2f%n", width);
        System.out.printf("  Area = %.2f, Perimeter = %.2f, Diagonal = %.2f%n",
                getArea(), getPerimeter(), getDiagonal());
    }
}
