package ru.tet.templating;

import java.util.Date;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.STRawGroupDir;

import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;
import ru.tet.beans.User;

public class D_ST4 extends DemoBase {

	public void test1() throws Exception {
		/*
		ST - базовые примеры
		 */

		User user = DemoAuxObjectSamples.createTestUserBean();
		List<Employee> employees = DemoAuxObjectSamples.createEmployeeList();

		ST st1 = new ST("Hello, <name> (<user.name.first>) \nnumber=<n>, double=<d>, date=<sd>");
		st1.add("name", "World");
		st1.add("user", user);
		st1.add("n", 127.0);
		st1.add("d", 1223.45688);
		st1.add("sd", new Date());

		String result = st1.render();
		log2(result);

		st1 = new ST("""
				<if(user.verified)>
				<user.name.first> is safe!
				<else>
				not safe!
				<endif>
				    		""");
		st1.add("user", user);
		result = st1.render();
		log2(result);

		st1 = new ST("<employees:{u|<%i>. <u.id>: <u.firstName>;\n}>");
		st1.add("employees", employees);
		result = st1.render();
		log2(result);

		result = ST.format("<%1>:<%2>", "Malki", "11 22 33");
		log2(result);

	}

	public void test2() throws Exception {
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
	public void test5() throws Exception {
		/*
		С заданием своих символов-ограничителей
		 */
		
		User user = DemoAuxObjectSamples.createTestUserBean();

		ST t1 = new ST("Hello, $user.name.first$", '$', '$');
		t1.add("user", user);

		String result = t1.render();
		log2(result);

		t1 = new ST("""
				$if(user.verified)$
				$user.name.first$ is old!
				$else$
				not old!
				$endif$
				    		""", '$', '$');
		t1.add("user", user);
		result = t1.render();
		log2(result);
		
		
		
		
		
		
	}
	
	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_ST4.class);
	}

}
