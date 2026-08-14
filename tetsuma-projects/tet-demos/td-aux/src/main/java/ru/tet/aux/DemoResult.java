package ru.tet.aux;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DemoResult {

	public Object s1;
	public Object s2;
	public Object s3;
	public Object s4;
	public Object s5;
	public Object s6;
	public Object s7;
	public Object s8;
	public Object s9;

	public void set(Object... values) {

		for (int i = 0; i < values.length; i++) {
			set(i+1, values[i]);
		}
		
	}

	public void set(int ind, Object val) {

		switch (ind) {
		case 1:
			s1 = val;
			break;
		case 2:
			s2 = val;
			break;
		case 3:
			s3 = val;
			break;
		case 4:
			s4 = val;
			break;
		case 5:
			s5 = val;
			break;
		case 6:
			s6 = val;
			break;
		case 7:
			s7 = val;
			break;
		case 8:
			s8 = val;
			break;
		case 9:
			s9 = val;
			break;
		}

	}
	

	public static void main(String[] args) throws Exception {

		DemoResult r = new DemoResult();
		r.s1 = IntStream.of(55).toArray();
		r.s2 = "hi";
		r.s3 = IntStream.of(5, 7, 11, 13).toArray();
		r.s4 = Stream.of("yanineko", "tabako", "ski");
		//fixResult(r);

	}

}
