package ru.tet.sourcebuddy;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.javax0.sourcebuddy.Compiler;

import ru.tet.utils.TetIOUtils;

public class SBTest2 {
	
	
    @Test
    @DisplayName("source code compiles")
    public void goodSimpleCode() throws Exception {
		String source = TetIOUtils.loadResourceAsString("sourcebuddy/Test3.java");
        
        Class<?> newClass = Compiler.compile("sourcebuddy.Test3", source);
        Object object = newClass.getConstructor().newInstance();
        Method f = newClass.getMethod("a");
        String s = (String) f.invoke(object);
        System.out.println(s);
    }
	
	
    @Test
    @DisplayName("source code compiles without specifying class name")
    public void goodSimpleCodeWoClassname() throws Exception {
    	
		String source = TetIOUtils.loadResourceAsString("sourcebuddy/Test1.java");
        Class<?> newClass = Compiler.compile(source);
        Object object = newClass.getConstructor().newInstance();
        Method f = newClass.getMethod("a");
        String s = (String) f.invoke(object);
        System.out.println(s);
    }	
    
	
    @Test
    @DisplayName("source code compiles providing the path to the file")
    public void goodSimpleCodeWithPathToFile() throws Exception {
    	
        Path path = Paths.get(this.getClass().getClassLoader().getResource("sourcebuddy/Test1.java").getPath());
    	
        Class<?> newClass = Compiler.java().from(path).compile().load().get();
        Object object = newClass.getConstructor().newInstance();
        Method f = newClass.getMethod("a");
        String s = (String) f.invoke(object);
        System.out.println(s);
    }    
    
	
    
    
    
    
    @Test
    @DisplayName("source code with subclasses works fine")
    public void sourceCodeWithSubclass()
            throws Exception {
		String source = TetIOUtils.loadResourceAsString("sourcebuddy/Test4.java");
		
        Class<?> newClass = Compiler.compile("sourcebuddy.Test4", source);
        Object object = newClass.getConstructor().newInstance();
        Method f = newClass.getMethod("method");
        int i = (int) f.invoke(object, (Object[]) null);
        System.out.println(i);
    }    
    
    
    
    
}
