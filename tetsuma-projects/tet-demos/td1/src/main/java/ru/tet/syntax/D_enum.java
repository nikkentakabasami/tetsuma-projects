package ru.tet.syntax;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import ru.tet.aux.swing.DemoBase;

/*
enum SortType {
	ASC(1), DESC(-1);

	@Getter
	int value;

	SortType(int i) {
		this.value = i;
	}
}


enum GoColor {

	BLACK {
		public String cname() {
			return "Black";
		}
	},
	WHITE {
		public String cname() {
			return "White";
		}
	};

	public abstract String cname();

}

enum Operation {
	PLUS {
		double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS {
		double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES {
		double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE {
		double apply(double x, double y) {
			return x / y;
		}
	};

	abstract double apply(double x, double y);
}

*/

enum SortType {
	ASC, DESC, NO
}

//Ассоциация констант с элементами перечисления
enum Day {
	MONDAY("Понедельник", 1), TUESDAY("Вторник", 2), WEDNESDAY("Среда", 3);

	private final String title;
	private final int order;

	Day(String dow, int order) {
		this.title = dow;
		this.order = order;
	}

	public String title() {
		return title;
	}

	public int order() {
		return order;
	}
}

//constant-specific method implementations -реализация перечислений, при которой каждому элементу задаётся собственное поведение
enum BasicOperation {

	PLUS("+") {
		double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		double apply(double x, double y) {
			return x / y;
		}
	};

	private final String symbol;

	BasicOperation(String symbol) {
		this.symbol = symbol;
	}

	abstract double apply(double x, double y);

	@Override
	public String toString() {
		return symbol;
	}

}

public class D_enum extends DemoBase {

	Integer someField = 67;

	static Integer someMethod() {
		return 55;
	}

	public void test1() throws Exception {
		/*
		 */
		//Day.MONDAY.dow

		Day d1;
		logEval1(
				d1 = Day.MONDAY,
				d1.name(),
				d1.ordinal(),
				d1.order(),
				d1.title(),
				
				Day.values(),
				Day.valueOf("TUESDAY"),

				BasicOperation.PLUS,
				BasicOperation.PLUS.apply(5, 7),
				BasicOperation.values()

		);

	}

	public void test2() throws Exception {
		/*
		
		 */
		
		Map<SortType, String> m1 =new EnumMap<SortType, String>(SortType.class);

		m1.put(SortType.ASC, "a");
		m1.put(SortType.DESC, "b");
		m1.put(SortType.NO, "c");
		
		
		
		EnumSet<SortType> set1 = EnumSet.allOf(SortType.class);		
		EnumSet<SortType> set2 = EnumSet.range(SortType.ASC, SortType.DESC);
		
		//пустая коллекция
		EnumSet<SortType> set3 = EnumSet.noneOf(SortType.class);
		
		EnumSet<SortType> set4 = EnumSet.of(SortType.DESC);
		
		logEval1(

				m1,
				set1,
				set2,
				set3,
				set4
		);
		

	}

	public void test3() throws Exception {
		/*
		
		 */
		

    String p1 = ArrayUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    String p2 = URLDecoder.decode(p1, StandardCharsets.UTF_8);
		
    logEval1(

    		p1,
    		p2
		);

    
		
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_enum.class);
	}

}
