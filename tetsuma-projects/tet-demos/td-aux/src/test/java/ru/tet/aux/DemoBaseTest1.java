package ru.tet.aux;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import ru.tet.aux.swing.DemoBase;

/**
 * Тест для демонстрации использования DemoBase
 */
public class DemoBaseTest1 extends DemoBase {

	List<String> demos;
	
	//вспомогательный класс, привязанный к первому тесту
	@AuxTest
	class testClass {}
	
	
	//здесь добавляют кнопки и прочие элементы на controlPanel
	@Override
	protected void doInitControlPanel() throws Exception {
		
		demos = new ArrayList<>();
		demos.add("demo1");
		demos.add("my demo 2");
		
		String[] demosArray = demos.toArray(new String[0]);
		
		controlPanel.addComboBox(demosArray, e->{
			int ind = controlPanel.comboBox1.getSelectedIndex();
			
			String val = (String)controlPanel.comboBox1.getSelectedItem();
			log2("ind:",ind,"val:",val);
			
		},"test combobox:");
		controlPanel.comboBox1.setPreferredSize(new Dimension(400, 20));
		
		
		controlPanel.newHorizontalBox();
		
		addTest1Button(null);
		addTest2Button(null);
	}
	
	//инициализация, которая будет выполняться перед тестами.
	//будет выводиться в разделе исходников
	@Override
	protected void doInit() throws Exception {
		
		//комментарии выделяются зелёным
		log1Splitter("first row");
		log2("Senri no michi mo ippo kara");
		log2("ishou kou narite, ban kotsu karu");
		log2("final","1","2","3","4");
		
	}
	

	@Override
	public void test1() throws Exception {
		
		double result = Math.pow(7, 7);
		
		log2("test1 result:",result);
		log2Splitter("my splitter");
		log2("several","different","messages","!");
	}
	
	@Override
	public void test2() throws Exception {

		//Эта функция позволяет компилировать, выполнять и выводить в лог короткие выражения.
		logEval2("""
				//кубический корень
				Math.cbrt(254)

				//деление с округлением вверх
				Math.ceilDiv(22, 3)
				""");		
		
	}
	
	public static void main(String[] args) {
		DemoBase.run(DemoBaseTest1.class);
		
		
	}	
	
	
		
	
	
}
