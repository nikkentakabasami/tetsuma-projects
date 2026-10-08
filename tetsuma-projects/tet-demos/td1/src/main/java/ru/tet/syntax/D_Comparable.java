package ru.tet.syntax;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.comparators.FixedOrderComparator;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.DemoAuxObjectSamples;
import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;

public class D_Comparable extends DemoBase {

	class EmplComparator implements Comparator<Employee> {

		@Override
		public int compare(Employee firstPlayer, Employee secondPlayer) {
			return Integer.compare(firstPlayer.getAge(), secondPlayer.getAge());
		}

	}

	public void test1() throws Exception {
		/*
		 */
		EmplComparator comp1 = new EmplComparator();
		List<Employee> list = DemoAuxObjectSamples.createEmployeeList();
		
		
		
		
		list.sort(comp1.reversed());
		log2(list);
		log2Splitter();

		//компаратор через лямбда выражения
		//минус использования вычитания в том, что может произойти integer overflow
		Comparator<Employee> comp2 = (o1, o2) -> o1.getId() - o2.getId();
		list.sort(comp2.reversed());
		log2(list);
		log2Splitter();
		
		Comparator<Employee> comp3 = Comparator.comparingInt(obj -> obj.getAge());
		list.sort(comp3);
		log2(list);
		log2Splitter();
		
		
		Comparator<Employee> comp4 = Comparator.comparing(e->e.getId());
		list.sort(comp4);
		log2(list);
		log2Splitter();
		
		Comparator<Employee> comp5 = Comparator.nullsFirst(comp1);
		list.add(null);
		list.sort(comp5);
		log2(list);
		

	}

	public void test2() throws Exception {
		/*
		
		 */
		
		//сортировка по длине строки
		Set<String> treeSet = new TreeSet<>(Comparator.comparing(String::length));

		List<String> apples = DemoAuxDataSamples.makeApplesList();
		treeSet.addAll(apples);

		log2(treeSet);

	}

	public void test3() throws Exception {
		/*
		
		 */

//		List<Integer> l1 = DemoAuxDataSamples.makeNumbersList(10);
		List<Integer> l1 = DemoAuxDataSamples.numbersRandomList;
		
	   FixedOrderComparator<Integer> foc1 = new FixedOrderComparator<>(3,5,7,8,1,2,4,6,9);
	   
	   l1.sort(foc1);
	   logEval1(
	  		 l1

		);

	   
		
		
		
		
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	public static void main(String[] args) {
		DemoBase.run(D_Comparable.class);
	}

}
