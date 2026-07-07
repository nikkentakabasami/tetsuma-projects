package examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiscTests {

	static void testRegex(String input, String regex) throws Exception {

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			System.out.format("'%s' (%d,%d)%n", matcher.group(), matcher.start(), matcher.end());
		}
	}

	public static void main(String[] args) throws Exception {

		String regex = "^\\d+(?=( |_|-))";

		testRegex("123 myfile desc", regex);
		testRegex("7777_myfile desc", regex);
		testRegex("55myfile desc", regex);
	}

}
