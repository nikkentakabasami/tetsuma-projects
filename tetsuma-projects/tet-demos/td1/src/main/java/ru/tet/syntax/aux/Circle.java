package ru.tet.syntax.aux;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Circle implements Shape {

	double r;
	
	
	@Override
	public double area() {
		return Math.PI*r*r;
	}
	
	
	void testMethod() {
		
		//константа из интерфейса
		int m = JANUARY;
		
		//статический метод из интерфейса
		int horsePower = Shape.getHorsePower(3, 23);
		
	}
	
}
