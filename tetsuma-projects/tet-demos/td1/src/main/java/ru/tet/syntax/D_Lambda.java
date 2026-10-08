package ru.tet.syntax;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_Lambda extends DemoBase {

	public void test1() throws Exception {
		/*
		 */

		Map<String, Integer> m1 = DemoAuxDataSamples.makeSalariesMap();

		log2(m1);
		
		m1.replaceAll((key, oldValue) -> 
		  key.equals("Freddy") ? oldValue : oldValue + 10000);		
		
		logEval1(
				m1
		);

		
		m1.forEach((key, val) -> log2(key,val));		
		
		
	}

	public void test2() throws Exception {
		/*
		
		 */
		
		ToDoubleBiFunction<String, Integer> f = (a,b) -> b.doubleValue()/3;
		double r1 = f.applyAsDouble("hi",44);
		
		log2(r1);
		


	}

	public void test3() throws Exception {
		/*
		
		 */
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
		DemoBase.run(D_Lambda.class);
	}

}
