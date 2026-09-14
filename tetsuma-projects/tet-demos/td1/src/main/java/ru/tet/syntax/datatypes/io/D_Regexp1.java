package ru.tet.syntax.datatypes.io;

import java.awt.EventQueue;
import java.util.regex.Pattern;

import ru.tet.aux.swing.DemoBase;

public class D_Regexp1 extends D_RegexpBase {

	public static String currentRegexps = """

			//

			(?i)tenka #поиск без учёта регистра

			сергей
			(?i)сергей #плохо работает




			Людовик
			(?m)^Людовик



						""";

	/*
	testRegex("Treehouse", "(?i)tree");
	testRegex("Treehouse", "tree");
	
	// многострочный поиск
	testRegex("abc\nabc", "(?m)^abc$");
	
	// комментарии
	testRegex("matter", ".at(?x)#match hat, cat, and so on");
	* 
	*/

	public D_Regexp1() {
		super(currentRegexps);
	}

	public void test2() throws Exception {
		/*
		case insentitive через флаги
		 */

		execRegex(testText, "сергей #case insentitive with flags", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

		/*
		case insentitive через вложенные флаговые выражения
		 */

		execRegex(testText, "(?i)людовик #");
		execRegex(testText, "(?i)tenka #");

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
		DemoBase.run(D_Regexp1.class);
	}

}
