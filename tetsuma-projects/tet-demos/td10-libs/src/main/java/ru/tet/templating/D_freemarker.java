package ru.tet.templating;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;
import ru.tet.beans.User;

public class D_freemarker extends DemoBase {

	Configuration cfg;
	
	class IndexOfMethod implements TemplateMethodModelEx {

		public TemplateModel exec(List args) throws TemplateModelException {
			if (args.size() != 2) {
				throw new TemplateModelException("Wrong arguments");
			}
			SimpleScalar str = (SimpleScalar) args.get(1);
			SimpleScalar ss = (SimpleScalar) args.get(0);

			return new SimpleNumber(str.getAsString().indexOf(ss.getAsString()));

		}
	}
	
	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
		
		cfg = new Configuration(Configuration.VERSION_2_3_27);
		
		ClassTemplateLoader tl = new ClassTemplateLoader(getClass().getClassLoader(), "/fmt");
		cfg.setTemplateLoader(tl);

		//		cfg.setDirectoryForTemplateLoading(new File("path/to/templates"));
		
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
	}
	

	
	
	public void test1() throws Exception {
		/*
		 */

		Map<String, Object> root = new HashMap<>();
		root.put("title", "ingaritsu");

		User user = DemoAuxObjectSamples.createTestUserBean();
		root.put("user", user);
		root.put("date", "05-2006");

		List<Employee> employees = DemoAuxObjectSamples.createEmployeeList();
		root.put("employees", employees);
		
		//добавление функций
		root.put("indexOf", new IndexOfMethod());

		Template template = cfg.getTemplate("test1.ftl");

		//		Writer out = new OutputStreamWriter(System.out);
		StringWriter sw = new StringWriter();

		template.process(root, sw);

		log2(sw.toString());

	}

	public void test2() throws Exception {
		/*
		
		 */


		Map<String, Object> root = new HashMap<>();
		Template template = cfg.getTemplate("test2.ftl");

		StringWriter sw = new StringWriter();
		template.process(root, sw);
		log2(sw.toString());
		
		
	}

	public void test3() throws Exception {
		/*
		
		 */
		Map<String, Object> root = new HashMap<>();
		Template template = cfg.getTemplate("test3.ftl");

		StringWriter sw = new StringWriter();
		template.process(root, sw);
		log2(sw.toString());
	}

	public void test4() throws Exception {
		/*
		
		 */
	}


	public static void main(String[] args) {
		DemoBase.run(D_freemarker.class);
	}

}
