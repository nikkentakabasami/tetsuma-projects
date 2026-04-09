package ru.tet.sourcebuddy;

import com.javax0.sourcebuddy.Compiler;
import com.javax0.sourcebuddy.Compiler.CompileException;

import ru.tet.sourcebuddy.auxcl.Talker;

public class SBTest1 {

	
	public static void main(String[] args) throws Exception {
		
		String source = """
		        package sb;
				import ru.tet.sourcebuddy.auxcl.Talker;

		        public class MyClass implements Talker {
		            @Override // comment
		            public void say() {
		                System.out.println("Hello, Buddy!");
		          }
		        }""";
//		Class<?> myClassClass = Compiler.compile(source);
		Class<Talker> myClassClass = Compiler.compile(source, Talker.class);		
		Talker myClass = myClassClass.getConstructor().newInstance();
		myClass.say();		
		
		
	}
	
	
	
	
}
