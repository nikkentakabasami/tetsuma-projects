package ru.tet.syntax;

import ru.tet.syntax.aux.Circle;
import ru.tet.syntax.aux.Shape;
import ru.tet.syntax.aux.Square;

public class D_Interface {

	public static void main(String[] args) {

		Circle c = new Circle(20);
		c.printShapeInfo();

		Square s = new Square(15);
		s.printShapeInfo();


		boolean isShape = (s instanceof Shape);
		System.out.println("s isShape:"+isShape);
		
		
		//константа из интерфейса
		int m = Shape.JANUARY;
		
		//статический метод из интерфейса
		int horsePower = Shape.getHorsePower(3000, 23);

		System.out.format("month: %d, power: %d", m, horsePower);
		

	}

}
