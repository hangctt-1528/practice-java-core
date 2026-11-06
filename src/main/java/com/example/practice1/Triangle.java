package com.example.practice1;

public class Triangle extends Shape {

    public Triangle(double base, double height) {
        super(base, height);
    }

    // Area = (base * height) / 2
    public double getArea() {
        return width * height / 2;
    }

    // Hypotenuse = √(base² + height²)
    public double getHypotenuse() {
        return Math.sqrt(width * width + height * height);
    }

    // Perimeter = base + height + hypotenuse
    public double getPerimeter() {
        return width + height + getHypotenuse();
    }

    @Override
    public void display() {
        System.out.printf("Triangle: base = %.2f, height = %.2f%n", width, height);
        System.out.printf("  Area = %.2f, Hypotenuse = %.2f, Perimeter = %.2f%n",
                getArea(), getHypotenuse(), getPerimeter());
    }
}
