package ru.tet.sourcebuddy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.tet.sourcebuddy.DemoEvalUtils;
import ru.tet.sourcebuddy.EvalResult;

public class DemoEvalUtilsTest2 {

    @Test
    @DisplayName("multiline eval")
	public void test1() throws Exception {
    	String code = """
    			//random
    			Math.random();
    			
    			////деление с округлением вверх
    			Math.ceilDiv(22, 3);
    			""";
    	
    	
    	List<EvalResult> ers = DemoEvalUtils.evalMultiExpression(code);
    	for(EvalResult er:ers) {
    		System.out.println(er.getComments());
    		System.out.println(er.getExpression()+" = "+er.getResult());
    	}
    	
    	
	}

    
    
    
    
}
