package ru.tet.templating;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;

public class D_ST4_Syntax2 extends DemoBase {

	void testSTGroup(String groupCode, String... args) {

		log2Splitter();

		log2(groupCode);
		log2Splitter();

		List<Employee> employees = DemoAuxObjectSamples.createEmployeeList();
		employees.add(null);

		List<String> apples = DemoAuxDataSamples.makeApplesList(4);

		STGroup group = new STGroupString(groupCode);

		ST st = group.getInstanceOf("test1");
		st.add("emps", employees);
		st.add("apples", apples);

		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				st.add("p" + (i + 1), args[i]);
			}

		}

		log2(st.toString());

		//можно задать максимальную длину строки текста
		log2(st.render(10));

	}

	public void test1() throws Exception {


	}

	public void test2() throws Exception {
		/*
		
		 */
		String g =
				"""
						parens(x) ::= "(<x>)"

						test1(emps,apples) ::= <<
						t1:
						<["a", "b", "c"]:parens()>
						t2:
						<parens(["a", "b", "c"])>
						t3:
						<apples:parens(); separator=\", \">

						>>
								""";

		testSTGroup(g);
		//		testSTGroup(g, "test2");

	}

	public void test3() throws Exception {

		/*
		Задаём другие delimiters.
		
		%i%
		 */

		String g = """
				delimiters "%", "%"
				getEmpDep(emp) ::= <<department of %emp.department%>>
				empToStr(emp) ::= <<%emp.id%: %emp.firstName%, %getEmpDep(emp)%>>
				test1(emps,apples) ::= <<
				%emps:{emp | %i%) %empToStr(emp)%}; separator=",\n"%
				>>

				class(name,members="...",sup="Object") ::= "class <name> extends <sup> { <members> }"
				""";
		testSTGroup(g);

	}

	public void test4() throws Exception {
		/*
		Регионы
		В шаблонах можно выделить регион -именованный участок шаблона. После чего в подгруппе этот регион можно переопределить
		 */

		String g = """

					method1(name,code,expr) ::= <<
					public void <name>() {
					    <@preamble()>
					    <code>
						if (<@eval1><expr><@end>) {
						  a = 1;
						}
					}
					>>

					@method1.preamble() ::= <<System.out.println("enter");>>
					@method1.eval1() ::= "trackAndEval(<expr>)"
				""";
		STGroup group = new STGroupString(g);
		ST st = group.getInstanceOf("method1");
		st.add("name", "MyFunc1");
		st.add("expr", "a>7");
		st.add("code", "f1=777;");
		log2(st.render());

	}

	@Override
	public void test5() throws Exception {

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
		options().logSources = false;
		frame.textAreaSP.setDividerLocation(100);
	}

	public static void main(String[] args) {
		DemoBase.run(D_ST4_Syntax2.class);
	}

}
