package ru.tet.aux;

import javax.swing.JButton;

public interface DemoAuxFunctions {

	//заготовки под тесты
	default void test1() throws Exception {
	}

	default void test2() throws Exception {
	}

	default void test3() throws Exception {
	}

	default void test4() throws Exception {
	}

	default void test5() throws Exception {
	}

	/**
	 * Запуск теста с заданным номером.
	 * 
	 * @param testNo
	 * @throws Exception
	 */
	default void test(int testNo) throws Exception {

		if (testNo <= 0 || testNo > 5) {
			System.out.println("bad test no: " + testNo);
			return;
		}

		beforeTest(testNo);

		switch (testNo) {
		case 1:
			test1();
			break;
		case 2:
			test2();
			break;
		case 3:
			test3();
			break;
		case 4:
			test4();
			break;
		case 5:
			test5();
			break;
		}
		//		lastTestName = testName;

		afterTest(testNo);
	}

	void beforeTest(int testNo) throws Exception;

	void afterTest(int testNo) throws Exception;


	default String nvl(String s, String mn) {
		return s != null ? s : mn;
	}


	//------------------добавление кнопок (они не обязательны)----------------------
	
	JButton addButton(String title, DemoActionListener al);
	
	default JButton addTestButton(String title, int testNo) {
		String testName = "test" + testNo;
		return addButton(nvl(title, testName), event -> {
			test(testNo);
		});
	}

	default JButton addTest1Button(String title) {
		return addTestButton(title, 1);
	}

	default JButton addTest2Button(String title) {
		return addTestButton(title, 2);
	}

	default JButton addTest3Button(String title) {
		return addTestButton(title, 3);
	}

	default JButton addTest4Button(String title) {
		return addTestButton(title, 4);
	}

	default JButton addTest5Button(String title) {
		return addTestButton(title, 5);
	}

}
