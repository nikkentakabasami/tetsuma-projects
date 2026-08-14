package ru.tet.syntax.aux;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Square implements Shape {

	double side;

	@Override
	public double area() {
		return side * side;
	}

	@Override
	public void printShapeInfo() {
		System.out.println("Квадрат со стороной: " + side + ", площадь: " + area());
	}
}
