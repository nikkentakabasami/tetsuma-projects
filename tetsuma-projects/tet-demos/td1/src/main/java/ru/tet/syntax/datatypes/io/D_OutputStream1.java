package ru.tet.syntax.datatypes.io;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_OutputStream1 extends DemoBase {

	public void test1() throws Exception {
		/*
		ByteArrayOutputStream
		 */

		ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
		bos1.write("hello!".getBytes());
		bos1.write(123);
		
		bos1.close();  //ничего не делает
		
		byte[] data = bos1.toByteArray();
		
		log2(Arrays.toString(data));
		
		

	}

	public void test2() throws Exception {
		/*
		
		 */


	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_OutputStream1.class);
	}

}
