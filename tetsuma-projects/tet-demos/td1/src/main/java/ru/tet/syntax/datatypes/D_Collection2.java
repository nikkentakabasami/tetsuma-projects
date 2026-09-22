package ru.tet.syntax.datatypes;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import ru.tet.aux.swing.DemoBase;

public class D_Collection2 extends DemoBase {

	public void test1() throws Exception {
		/*
		 */
		
		List<Integer> l1 = new ArrayList<>(IntStream.range(1, 10).boxed().toList());
		log2(l1);

		Collections.reverse(l1);
		log2(l1);
		
		
		Collections.shuffle(l1);
		log2(l1);
		
		Collections.sort(l1);
		log2(l1);
		
		Collections.rotate(l1,3);
		log2(l1);
		Collections.rotate(l1,-5);
		log2(l1);

	}

	public void test2() throws Exception {
		/*
		
		 */
		BitSet bs1 = new BitSet(10);
		bs1.set(1);
		bs1.set(3,5);

		logEval1(
				bs1,
				bs1.cardinality(),
				bs1.length()
		);

		

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
		DemoBase.run(D_Collection2.class);
	}

}
