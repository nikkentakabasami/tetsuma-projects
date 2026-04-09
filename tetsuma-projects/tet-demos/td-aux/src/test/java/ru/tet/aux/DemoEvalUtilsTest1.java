package ru.tet.aux;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.tet.sourcebuddy.DemoEvalUtils;
import ru.tet.sourcebuddy.EvalOptions;

public class DemoEvalUtilsTest1 {

    @Test
    @DisplayName("simplest eval")
	public void test1() throws Exception {
    	DemoEvalUtils.eval("Math.random()");
    	DemoEvalUtils.eval("r = Math.ceilDiv(22, 3);");
    	
	}

    
    @Test
    @DisplayName("eval with imports")
	public void test2() throws Exception {
    	
    	EvalOptions options = new EvalOptions();
    	options.getImportClasses().add(LocalDateTime.class);
    	
    	DemoEvalUtils.eval("LocalDateTime.now()",options);
    	
	}
    
    @Test
    @DisplayName("multiline eval with imports")
	public void test3() throws Exception {
    	
    	
    	EvalOptions options = new EvalOptions();
    	options.getImportClasses().add(Calendar.class);
    	options.getImportClasses().add(Date.class);
    	
		String code = """
			Date d = new Date();
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(d);
	        cal.add(Calendar.MONTH, 1);
	        r = cal.getTime(); """;
    	
    	DemoEvalUtils.eval(code,options);
    	
	}	
    
    
    
}
