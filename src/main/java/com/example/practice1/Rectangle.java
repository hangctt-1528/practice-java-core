package com.example.practice1;

public class Rectangle extends Shape {

    public Rectangle(double width, double height) {
        super(width, height); // call the Shape constructor
    }

    // Area = w * l
    public double getArea() {
        return width * height;
    }

    // Perimeter = 2 * (l + w)
    public double getPerimeter() {
        return 2 * (height + width);
    }

    // Extension: Diagonal = √(w² + l²)
    public double getDiagonal() {
        return Math.sqrt(width * width + height * height);
    }

    @Override
    public void display() {
        System.out.printf("Rectangle: width = %.2f, height = %.2f%n", width, height);
        System.out.printf("  Area = %.2f, Perimeter = %.2f, Diagonal = %.2f%n",
                getArea(), getPerimeter(), getDiagonal());
    }
}
