package com.example.practice1;

public class Circle extends Shape {

    // For a circle, width = height = diameter = 2 * radius
    public Circle(double radius) {
        super(radius * 2, radius * 2);
    }

    public double getRadius() {
        return width / 2;
    }

    // Area = π * r²
    public double getArea() {
        return Math.PI * getRadius() * getRadius();
    }

    // Circumference = d * 3.14
    public double getCircumference() {
        return width * 3.14;
    }

    @Override
    public void display() {
        System.out.printf("Circle: radius = %.2f%n", getRadius());
        System.out.printf("  Area = %.2f, Circumference = %.2f%n", getArea(), getCircumference());
    }
}
