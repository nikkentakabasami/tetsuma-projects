package ru.tet.utils;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class TetIOUtilsTest {

	
	@Test
	public void test1() throws IOException {
		
		String resourceAsString = TetIOUtils.loadResourceAsString("tr/Test1.java");
		System.out.println(resourceAsString);
		
		
	}
	
	
	
}
