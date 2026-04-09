package ru.tet.sourcebuddy;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javax0.sourcebuddy.Compiler;

import ru.tet.sourcebuddy.aux.EvalFunction;
import ru.tet.sourcebuddy.aux.EvalFunction2;
import ru.tet.sourcebuddy.templ.EvalTemplate1;
import ru.tet.sourcebuddy.templ.EvalTemplate2;
import ru.tet.utils.TetSourceUtils;

/**
 * Возволяет выполнять java-выражения, по аналогии с js: eval(code)
 * 
 */
public class DemoEvalUtils {

	static Logger logger = LogManager.getLogger();

	public static Object eval(String code) throws Exception {
		return eval(code, null);
	}
	
	public static Object eval(String code, EvalOptions options) throws Exception {
		if (options==null) {
			options = new EvalOptions();
		}
		
		Class<?> templateClass = options.templateClass;
		if (templateClass==null) {
			templateClass = EvalTemplate1.class; 
		}
		
		String template = TetSourceUtils.findSourceAsString(templateClass);
		
		code = code.trim();
		
		
		
		if (!code.contains("\n") && !code.startsWith("r=") && !code.startsWith("r =")) {
			code = "r = "+code;
			if (!code.endsWith(";")) {
				code = code+";";
			}
		}
		
		
		String source = template
				.replace("//code", code)
//				.replace("//code", "result="+code+";")
				.replaceFirst("package.*", "package sb;");
		
		if (options.importClasses.size()>0) {
			
			String imports = options.importClasses.stream()
			.map(cl->"import "+cl.getCanonicalName()+";\n")
			.collect(Collectors.joining());
			source = source.replace("//imports", imports);
		}
		
		logger.debug("-------------");
		logger.debug(source);
		logger.debug("-------------");
		
		Class<EvalFunction> myClassClass = Compiler.compile(source, EvalFunction.class);		
		EvalFunction myClass = myClassClass.getConstructor().newInstance();
		Object result = myClass.eval();
		
		logger.debug("code:"+code);
		logger.debug("result:"+result);
		
		return result;
		
	}	

	public static List<EvalResult> evalMultiExpression(String code) throws Exception {
		return evalMultiExpression(code, null);
	}
	
	
	public static List<EvalResult> evalMultiExpression(String code, EvalOptions options) throws Exception {
		if (options==null) {
			options = new EvalOptions();
		}
		
		List<EvalResult> result = new ArrayList<>();
		
		Class<?> templateClass = options.templateClass;
		if (templateClass==null) {
			templateClass = EvalTemplate2.class; 
		}
		
		String template = TetSourceUtils.findSourceAsString(templateClass);
		
		code = code.trim();
		
		String[] lines = code.split("\n");
		
		StringBuilder comments = new StringBuilder();
		code="";
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim().replaceAll(";", "");
			if (line.length()==0) {
				continue;
			}
			if (line.startsWith("//")) {
				if (!comments.isEmpty()) {
					comments.append('\n');
				}
				comments.append(line);
				
				continue;
			}
			
			
			
			code+="r.add("+line+");\n";
			
			EvalResult er = new EvalResult();
			er.setExpression(line);
			if (comments.length()>0) {
				er.setComments(comments.toString());
			}
			result.add(er);
			comments.setLength(0);
			
		}
		
		String source = template
				.replace("//code", code)
//				.replace("//code", "result="+code+";")
				.replaceFirst("package.*", "package sb;");
		
		if (options.importClasses.size()>0) {
			
			String imports = options.importClasses.stream()
			.map(cl->"import "+cl.getCanonicalName()+";\n")
			.collect(Collectors.joining());
			source = source.replace("//imports", imports);
		}
		
		logger.debug("-------------");
		logger.debug(source);
		logger.debug("-------------");
		
		Class<EvalFunction2> myClassClass = Compiler.compile(source, EvalFunction2.class);		
		EvalFunction2 myClass = myClassClass.getConstructor().newInstance();
		List<Object> evalResults = myClass.eval();
		
		for (int i = 0; i < evalResults.size(); i++) {
			EvalResult er = result.get(i);
			Object r = evalResults.get(i);
			er.setResult(r);
		}
		
		logger.debug("code:"+code);
		logger.debug("result:"+Arrays.toString(evalResults.toArray()));		
		
		return result;		
		
	}
	
	
	
}
