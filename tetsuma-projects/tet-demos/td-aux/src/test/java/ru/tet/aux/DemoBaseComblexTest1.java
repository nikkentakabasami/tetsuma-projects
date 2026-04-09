package ru.tet.aux;

import ru.tet.aux.swing.DemoBase;
import ru.tet.aux.swing.DemoBaseComblex;

/**
 * Тест DemoBaseComblex.
 * Этот способ позволяет запускать несколько демо в одном фрейме.
 * Переключение между демками можно делать клавишами F1, F2.
 * 
 */
public class DemoBaseComblexTest1 extends DemoBaseComblex {

	public DemoBaseComblexTest1() {
		//задаём пакет, в котором нужно провести сканирование демок.
		super("ru.tet.aux");
	}
	
	
	
	public static void main(String[] args) {
		DemoBase.run(DemoBaseComblexTest1.class);
	}	
		
	
}
