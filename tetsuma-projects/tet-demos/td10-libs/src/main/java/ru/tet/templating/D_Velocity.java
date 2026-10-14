package ru.tet.templating;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;

public class D_Velocity extends DemoBase {

	public void test1() throws Exception {

		//настройки по умолчанию лежат в org/apache/velocity/runtime/defaults/velocity.properties 

		//Инициализируем  Velocity
		Properties p = new Properties();
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(p);

		//Создаём контекст со значениями плейсхолдеров
		VelocityContext context = new VelocityContext();
		context.put("name", new String("Velocity"));

		//Создаём шаблон (ресурс будет искаться в класспазе)
		Template template = Velocity.getTemplate("vel/test1.vm");

		//генерация документа
		StringWriter sw = new StringWriter();
		template.merge(context, sw);
		log2(sw.toString());

		log2Splitter();
		template = Velocity.getTemplate("vel/test2.vm");

		//генерация документа
		sw = new StringWriter();
		template.merge(context, sw);
		log2(sw.toString());

		log2Splitter();

		//генерация документа на основе шаблона в виде строки
		context.put("name", "Velocity");
		context.put("project", "Jakarta");
		String s = "We are using $project $name to render this.";
		sw = new StringWriter();
		Velocity.evaluate(context, sw, "myTemplate1", s);
		log2(sw.toString());

	}

	public void test3() throws Exception {
		/*
		
		 */
		
		
		Properties p = new Properties();
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(p);

		
		List<Employee> employees = DemoAuxObjectSamples.createEmployeeList();
		List<String> apples = DemoAuxDataSamples.makeApplesList(4);
		
		
		//Создаём контекст со значениями плейсхолдеров
		VelocityContext context = new VelocityContext();
		context.put("name", "My complex test");

		context.put("employees", employees);
		context.put("apples", apples);

		
		
		//Создаём шаблон (ресурс будет искаться в класспазе)
		Template template = Velocity.getTemplate("vel/test_emps.vm");

		//генерация документа
		StringWriter sw = new StringWriter();
		template.merge(context, sw);
		log2(sw.toString());
		
		


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
		DemoBase.run(D_Velocity.class);
	}

}
