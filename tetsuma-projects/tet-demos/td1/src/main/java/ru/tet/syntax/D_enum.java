package ru.tet.syntax;

import ru.tet.aux.swing.DemoBase;

public class D_enum extends DemoBase {

	Integer someField = 67;
	
	static Integer someMethod() {
		return 55;
	}
	
	enum Day {
		MONDAY("Понедельник", 1), TUESDAY("Вторник", 2), WEDNESDAY("Среда", 3);

		private final String dow;
		private final int order;

		// Конструктор
		Day(String dow, int order) {
			this.dow = dow;
			this.order = order;
			someMethod();
		}

		public String dow() {
			return dow;
		}

		public int order() {
			return order;
		}
	}

	public void test1() throws Exception {
		/*
		 */
		//Day.MONDAY.dow

		Day d1 = Day.MONDAY;
		logEval1(
				d1.name(),
				d1.order);

	}

	public void test2() throws Exception {
		/*
		
		 */

	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_enum.class);
	}

}
