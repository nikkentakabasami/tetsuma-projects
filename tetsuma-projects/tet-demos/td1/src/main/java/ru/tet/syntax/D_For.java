package ru.tet.syntax;

import java.util.Iterator;
import java.util.List;

public class D_For {

	public static void main(String[] args) {

		List<String> list = List.of("yanineko", "tabako", "ski");

		for (String val : list) {
			System.out.println(val);
		}

		//альтернативная итерация
		list.forEach(name -> System.out.println("forEach:"+name));
		
		for (int i = 0; i < 5; i++) {
			System.out.println("Iteration: " + i);
		}

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		String[] cars = { "Volvo", "BMW", "Ford" };
		for (String car : cars) {
			System.out.println(car);
		}

		
		
		//continue метка;  
		//Позволяет переходить на следующий шаг внешнего цикла, заданного меткой
		//break метка;  
		//Позволяет выйти из внешнего цикла, заданного меткой
		
		outer: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				
				if (j > i) {
					System.out.println("");
					//к следующей итерации for i 
					continue outer;
				}

				if (j==9) {
					//полностью закончить итерацию 
					break outer;
				}
				
				
				System.out.print(" " + (i * j));
			}
		}		

	}

}
