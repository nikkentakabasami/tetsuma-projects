package ru.tet.templating;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;

public class D_ST4_samples extends DemoBase {

	public void test1() throws Exception {
		/*
		Общий пример использования stg-файла
		 */

		List<Employee> employees = DemoAuxObjectSamples.createEmployeeList();
		List<String> apples = DemoAuxDataSamples.makeApplesList(4);
		
		STGroup group = new STGroupFile("st4/employees_page.stg");
		
		ST st = group.getInstanceOf("testPage");
		
		st.add("emps", employees);
		st.add("apples", apples);
		
		String s = st.render();
		log2(s);
		
		
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

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}
	
	public static void main(String[] args) {
		DemoBase.run(D_ST4_samples.class);
	}

}
