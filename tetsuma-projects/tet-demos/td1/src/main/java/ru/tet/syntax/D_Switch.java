package ru.tet.syntax;

public class D_Switch {

	public static void main(String[] args) {

		int day = 6;

		//старый синтаксис
		switch (day) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			System.out.println("Weekday");
			break;
		case 6:
		case 7:
			System.out.println("Weekend");
			break;
		default:
			System.out.println("Invalid day");
		}

		//похоже что так тоже можно
		switch (day) {
		case 1,2,3,4,5:
			System.out.println("Weekday");
			break;
		case 6,7:
			System.out.println("Weekend");
			break;
		default:
			System.out.println("Invalid day");
		}		
		
		
		
		
		
		//современный синтаксис
		String typeOfDay = switch (day) {
		case 1, 2, 3, 4, 5 -> "Weekday";
		case 6, 7 -> "Weekend";
		default -> "Unknown day";
		};
		System.out.println(typeOfDay);

		//yield - используется если нужно несколько строк кода
		String status = "PENDING";
		int calculatedValue = switch (status) {
		case "ACTIVE" -> 1;
		case "PENDING" -> {
			System.out.println("Processing...");
			yield 2;
		}
		default -> 0;
		};
		System.out.println("calculatedValue:" + calculatedValue);

		/*
		Pattern matching
		Эта фича доступна только с 21 версии.

		Object obj = Integer.valueOf(555);

		String r = switch (obj) {
		case Integer i -> "An integer: " + i;
		case String s -> "A string: " + s.toUpperCase();
		case null -> "Object is null"; // Explicit null handling
		default -> "Unknown type";
		};

		r = switch (n) {
		case Integer i when i > 0 -> "Positive integer";
		case Integer i -> "Zero or negative integer";
		default -> "Not an integer";
		};
		*/

	}

}
