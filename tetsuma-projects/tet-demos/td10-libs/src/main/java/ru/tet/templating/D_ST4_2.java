package ru.tet.templating;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;

public class D_ST4_2 extends DemoBase {

	public void test1() throws Exception {
		/*
		
		st.addAggr()
		Задание данных в списках, если нет подходящего класса
		 */

		ST st1 = new ST("<items:{it|<it.id>: <it.lastName>, <it.firstName>\n}>");
		st1.addAggr("items.{ firstName ,lastName, id }", "Ter", "Parr", 99);
		st1.addAggr("items.{firstName, lastName ,id}", "Tom", "Burns", 34);
		String result = st1.render();
		log2(result);

	}

	public void test2() throws Exception {
		/*
		
		 */

		List<Employee> employees = DemoAuxObjectSamples.createEmployeeList();

		String g = """
				getEmpDep(emp) ::= "department of <emp.department>"
				empToStr(emp) ::= "<emp.id>: <emp.firstName>, <getEmpDep(emp)><if(emp.old)> (is old!)<endif>"
				listEmps(emps) ::= "<emps:{emp | <i>) <empToStr(emp)>}; separator=\\",\n\\">"
				""";
		STGroup group = new STGroupString(g);
		ST st = group.getInstanceOf("listEmps");
		st.add("emps", employees);
		log2(st.render());

		/*
		Задаём другие delimiters.
		
		%i%
		
		 */

		g = """
				delimiters "%", "%"
				getEmpDep(emp) ::= <<department of %emp.department%>>
				empToStr(emp) ::= <<%emp.id%: %emp.firstName%, %getEmpDep(emp)%>>
				listEmps(emps) ::= <<%emps:{emp | %i%) %empToStr(emp)%,\n }%>>
				""";
		group = new STGroupString(g);
		st = group.getInstanceOf("listEmps");
		st.add("emps", employees);
		log2(st.render());

		g = """
				empToStr(emp) ::= "[<emp.id>]"
				test(emps) ::= "<emps:empToStr()>"
				test2(emps) ::= "<emps:empToStr(); separator=\\",\\">"
				""";
		group = new STGroupString(g);
		st = group.getInstanceOf("test");
		st.add("emps", employees);
		log2(st.render());

		st = group.getInstanceOf("test2");
		log2(st.toString());
		st.add("emps", employees);
		log2(st.render());

	}

	public void test3() throws Exception {
		/*
		
		 */
		List<Integer> numbersList = DemoAuxDataSamples.makeNumbersList(15);
		String s = ST.format(20, "int <%1>[] = { <%2; wrap, anchor, separator=\", \"> };", "a", numbersList);
		log2(s);

	}

	public void test4() throws Exception {
		/*
		
		 */

		String s = ST.format("""
				<["a", "b", "c"]>
				<first(["a", "b", "c"])>
						""");
		log2(s);

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_ST4_2.class);
	}

}
