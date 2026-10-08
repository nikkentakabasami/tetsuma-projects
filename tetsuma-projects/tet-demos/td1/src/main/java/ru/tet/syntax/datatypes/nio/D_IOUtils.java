package ru.tet.syntax.datatypes.nio;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ReferenceMap;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.collection.CompositeCollection;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_IOUtils extends DemoBase {

	public void test1() throws Exception {
		/*
		IOUtils
		статичные методы для работы с потоками
		 */

		FileInputStream is1 = new FileInputStream(Path.of("pom.xml").toFile());
		for (LineIterator it1 = IOUtils.lineIterator(is1, StandardCharsets.UTF_8); it1.hasNext();) {
			String line = it1.next();
			log2(line);

		}

	}

	public void test2() throws Exception {
		/*
		
		 */

		MultiSet<Integer> ms1 = new HashMultiSet<>();
		ms1.add(1);
		ms1.add(3);
		ms1.add(2, 3);
		ms1.setCount(3, 5);
		ms1.setCount(4, 5);

		ms1.remove(2);

		logEval1(
				ms1

		);

	}

	public void test3() throws Exception {
		/*
		
		 */

		//		BidiMap<Integer, String> m1 = new TreeBidiMap<>();
		BidiMap<Integer, String> m1 = new TreeBidiMap<>();
		m1.put(5, "five");
		m1.put(4, "four");
		m1.put(6, "six");
		m1.put(3, "three");

		BidiMap<String, Integer> m2 = m1.inverseBidiMap();

		logEval1(
				m1,
				m2,
				m1.getKey("five"));

		logEval2(
				m1.removeValue("six"),
				m1);

	}

	public void test4() throws Exception {
		/*
		
		 */

		MultiValuedMap<Integer, String> m1 = new ArrayListValuedHashMap<>();
		m1.put(1, "A");
		m1.put(1, "B");
		m1.put(1, "C");
		m1.put(2, "W");
		m1.put(3, "Z");
		Collection<String> v1 = m1.get(1);

		logEval1(
				v1,
				m1

		);
	}

	int counter;
	Map<Integer, String> rm1 = Collections.synchronizedMap(new ReferenceMap(ReferenceMap.SOFT, ReferenceMap.SOFT));
	//  Map<Integer, String> rm1 = new ReferenceMap(ReferenceMap.SOFT, ReferenceMap.SOFT);

	@Override
	public void test5() throws Exception {
		List<String> l1 = DemoAuxDataSamples.makeApplesList();
		List<String> l2 = DemoAuxDataSamples.makeNumbersListString();

		CompositeCollection<String> c1 = new CompositeCollection<>(l1, l2);

		log2(c1.toArray());

		/*
		ReferenceMap
		позволяет убирать значения сборщиком мусора
		 */

		for (int i = 0; i < 100000; i++) {
			counter++;
			rm1.put(counter, String.valueOf(counter));
		}
		System.gc();
		log2(rm1.size());

	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_IOUtils.class);
	}

}
