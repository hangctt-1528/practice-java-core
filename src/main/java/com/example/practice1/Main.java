package com.example.practice1;

public class Main {

    public static void main(String[] args) {
        Shape[] shapes = {
            new Shape(3, 4),
            new Rectangle(5, 2),
            new Circle(1.5),
            new Square(4),
            new Triangle(3, 4)
        };

        for (Shape shape : shapes) {
            shape.display();
        }
    }
}
