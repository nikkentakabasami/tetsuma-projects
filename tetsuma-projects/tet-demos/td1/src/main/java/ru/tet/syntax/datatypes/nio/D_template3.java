package ru.tet.syntax.datatypes.nio;

import java.util.Comparator;

import org.apache.commons.lang3.math.NumberUtils;

import ru.tet.aux.swing.DemoBase;
import ru.tet.beans.Employee;

public class D_template3 extends DemoBase {

	class EmplComparator implements Comparator<Employee> {

		@Override
		public int compare(Employee firstPlayer, Employee secondPlayer) {
			return Integer.compare(firstPlayer.getAge(), secondPlayer.getAge());
		}

	}

	public void test1() throws Exception {
		/*
		 */

		logEval1(
				NumberUtils.max(12, 77, 44),
				String.CASE_INSENSITIVE_ORDER

		);

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

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}

	public static void main(String[] args) {
		DemoBase.run(D_template3.class);
	}

}
