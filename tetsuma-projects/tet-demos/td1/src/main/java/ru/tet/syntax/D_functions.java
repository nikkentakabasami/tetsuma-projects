package ru.tet.syntax;

import java.util.Arrays;

import ru.tet.aux.swing.DemoBase;

public class D_functions extends DemoBase {

	void func1(String... values) {
		log2Splitter();
		log2("func1:", Arrays.toString(values));
		func2("hi", values);
	}

	void func2(String p1, String... values) {
		log2Splitter();
		//передача varargs в другую функцию
		log2("func2:", p1, ",", Arrays.toString(values));
	}

	public void test1() throws Exception {
		/*
		varargs (variable arguments)
		инструмент, позволяющий передавать в метод произвольное количество аргументов одного типа.
		
		Синтаксис: указывается тип данных, затем три точки (...) и имя параметра 
		(например, int... numbers).
		такой параметр всегда должен быть последним в списке аргументов метода.
		
		Под капотом: параметр с варгаргом работает как обычный массив.
		Если не передать аргументы, внутрь метода попадет пустой массив длины 0
		 */

		//Способы вызова:
			
		func1();
		func1("a", "b", "c");

	//явная передача массива
		String[] arr1 = { "one", "two", "three" };
		func1(arr1);

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
		DemoBase.run(D_functions.class);
	}

}
