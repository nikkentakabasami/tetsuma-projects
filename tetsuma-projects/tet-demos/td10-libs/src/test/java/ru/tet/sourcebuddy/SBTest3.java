package ru.tet.sourcebuddy;

import java.io.IOException;

import com.javax0.sourcebuddy.Compiler;

import ru.tet.sourcebuddy.auxcl.EvalFunction;
import ru.tet.sourcebuddy.templ.EvalTemplate1;
import ru.tet.utils.TetIOUtils;
import ru.tet.utils.TetSourceUtils;

public class SBTest3 {

	
	public static Object eval(String code) throws Exception {
		
		String template = TetSourceUtils.findSourceAsString(EvalTemplate1.class);
		String source = template
				.replace("//code", "result="+code+";")
				.replaceFirst("package.*", "package sb;");
		
		
//		String template = TetIOUtils.loadResourceAsString("sourcebuddy/EvalTemplate.java");
//		String source = template.replaceAll("<code>", code);
		
		System.out.println("-------------");
		System.out.println(source);
		System.out.println("-------------");
		
		Class<EvalFunction> myClassClass = Compiler.compile(source, EvalFunction.class);		
		EvalFunction myClass = myClassClass.getConstructor().newInstance();
		Object result = myClass.eval();
		
		System.out.println("code:"+code);
		System.out.println("result:"+result);
		
		return result;
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		eval("Math.random()");
		eval("Math.ceilDiv(22, 3)");
		
	}
	
	
	
	
}
