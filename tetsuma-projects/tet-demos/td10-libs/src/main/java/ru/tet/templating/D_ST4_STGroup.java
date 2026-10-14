package ru.tet.templating;

import java.util.Date;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.STGroupString;
import org.stringtemplate.v4.STRawGroupDir;

import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;
import ru.tet.beans.User;

public class D_ST4_STGroup extends DemoBase {

	public void test1() throws Exception {
		/*
		
		 */

		List<Employee> employees = DemoAuxObjectSamples.createEmployeeList();

		String g = """
				getEmpDep(emp) ::= << department of <emp.department> >>
				empToStr(emp) ::= << <emp.id>: <emp.firstName>, <getEmpDep(emp)><if(emp.old)> (is old!)<endif> >>
				listEmps(emps) ::= << <emps:{emp | <i>) <empToStr(emp)>}; separator=",\n"> >>
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
				empToStr(emp) ::= <<%emp.id%: %emp.firstName%, %getEmpDep(emp)%%if(emp.old)% (is old!)%endif%  >>
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

	@Override
	public void test2() throws Exception {
		
		
	}	
	
	
	public void test5() throws Exception {
		/*
		STGroupDir
		 */

		STGroup group = new STGroupDir("st4");
		ST st = group.getInstanceOf("decl");
		st.add("type", "int");
		st.add("name", "x");
		st.add("value", 0);
		String result = st.render(); //"int x = 0;"		
		log2(result);

	}

	public void test3() throws Exception {
		/*
		STRawGroupDir
		глючный способ.
		
		 */

		STRawGroupDir group = new STRawGroupDir("st4");
		ST st = group.getInstanceOf("decl2");
		st.add("type", "int");
		st.add("name", "x");
		st.add("value", 0);
		String result = st.render(); //"int x = 0;"		
		log2(result);
	}

	public void test4() throws Exception {
		/*
		STGroupFile
		 */

		STGroup group = new STGroupFile("st4/test.stg");
		ST st = group.getInstanceOf("decl");
		st.add("type", "int");
		st.add("name", "x");
		st.add("value", 22);
		String result = st.render();		
		log2(result);

	}

	
	

	
	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_ST4_STGroup.class);
	}

}
