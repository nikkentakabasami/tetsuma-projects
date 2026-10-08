package ru.tet.aux;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.io.IOUtils;

import ru.tet.beans.SuIdNameModel;

/**
 * Образцы данных для демок.
 */
public class DemoAuxDataSamples {

	public static String sampleString = "Съешь ещё этих мягких французских булок, да выпей же чаю";

	public static String sampleStringShort = "fujori dake no kishoutenketsu";
	public static String sampleStringLyric =
			"Houritsu mo chitsujo demo  kurutta yatsu ga tsukutteru, Masa ni FARUSU  fujouri dake no kishou tenketsu";
	public static String sampleStringJap = "法律も秩序でも　狂った奴が創ってる まさに ファルス 不条理だけの起承転結";

	//Строка с токенами, для тестирования StreamTokenizer
	public static String sampleStringTokenized =
			"(;GM[1.4] /comment\n  один-четыре\t分け前 \n\"quo ted1\" \n 'quoted 2' FF[4] CA[UTF-8]AP[CGoban:3]KM[0.50]TM[3600]";

	public static String multilineTestString = "привет //𝄞\nКак дела?\n2+6/5";

	public static String jsonString1 =
			"{\"enplantId\":121,\"enplantGuid\":\"D057056E385B8F72E050007F01014BBE\",\"reportId\":\"reportAlpha\",\"filterParams\":{\"параметр1\": \"因果\", \"параметр2\" : \"что то на русском\"},\"token\":\"someToken\"}";

	//url для тестирования get-запросов
	public static String url_get_example = "https://example.com";
	public static String url_get_httpbin = "https://httpbin.org/post";

	//url для тестирования post-запросов
	public static String url_post_httpbin = "https://httpbin.org/post";

	public static String[] tableHeadings1 = { "From", "Address", "Subject", "Size" };

	public static Object[][] tableData1 =
			{
					{ "Wendy", "Wendy@HerbSchildt.com", "Hello Herb", 287 },
					{ "Alex", "Alex@HerbSchildt.com", "Check this out!", 308 },
					{ "Hale", "Hale@HerbSchildt.com", "Found a bug", 887 },
					{ "Todd", "Todd@HerbSchildt.com", "Did you see this?", 223 },
					{ "Steve", "Steve@HerbSchildt.com", "I'm back", 357 },
					{ "Ken", "Ken@HerbSchildt.com", "Arrival time change", 512 } };

	public static String[] tableHeadings2 = { "String", "Integer", "Boolean" };

	public static Object[][] tableData2 =
			{
					{ "aaa", 12, true }, { "bbb", 5, false }, { "CCC", 92, true },
					{ "DDD", 0, false } };

	public static String[] tableHeadings3 = { "propName", "propValue" };

	public static Object[][] tableData3 =
			{
					{ "Wendy", "Wendy@HerbSchildt.com" },
					{ "Alex", "Alex@HerbSchildt.com" },
					{ "Hale", "Hale@HerbSchildt.com" } };

	public static String apples[] =
			{
					"Winesap", "Cortland", "Red Delicious", "Golden Delicious", "Gala", "Fuji",
					"Granny Smith", "Jonathan" };

	public static List<Integer> numbersList = IntStream.range(1, 10).boxed().toList();

	public static List<Integer> numbersRandomList = new Random(1).ints(20, 1, 10).boxed().collect(Collectors.toList());

	public static Integer[] numbersRandomArray = numbersRandomList.toArray(Integer[]::new);

	public static Map<Integer, String> makeMap1(int size) {
		Map<Integer, String> m1 = new HashMap<>();
		IntStream.range(0, size).forEach(v -> {
			int code = v + 'a';
			char c = (char) code;
			m1.put(v, "item_" + c);
		});
		return m1;
	}

	public static Map<String, Integer> makeSalariesMap() {

		Map<String, Integer> m1 = new HashMap<>();
		m1.put("John", 40000);
		m1.put("Freddy", 30000);
		m1.put("Samuel", 50000);
		m1.put("bob", 20000);
		return m1;
	}

	public static List<String> makeApplesList() {
		return makeApplesList(100);
	}

	public static List<String> makeApplesList(int size) {
		return Arrays.stream(apples).limit(size).collect(Collectors.toList());
	}

	public static List<String> makeNumbersListString() {
		return makeNumbersListString(10);
	}

	public static List<String> makeNumbersListString(int limit) {
		return IntStream.range(1, limit).boxed().map(String::valueOf).toList();
	}

	public static List<Integer> makeNumbersList(int limit) {
		return makeNumbersList(limit, 0);
	}
	
	public static List<Integer> makeNumbersList(int limit, int start) {
		return IntStream.range(start, start+limit).boxed().collect(Collectors.toList());
	}

	public static List<String> makeApplesListBig(int size) {
		List<String> data = new ArrayList<>();

		for (int i = 0; i < apples.length; i++) {
			String apple = apples[i];

			Random r = new Random();
			for (int j = 0; j < size; j++) {
				int randInt = r.nextInt(1, 100);
				String val = apple + "_" + randInt;
				data.add(val);
			}
		}
		return data;
	}

	public static List<SuIdNameModel> makeItemsList(int size) {
		List<SuIdNameModel> data = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			data.add(new SuIdNameModel(i, "my item " + i));

		}
		return data;
	}

	public static List<String> makeStringList(int size) {
		List<String> data = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			data.add("my item " + i);
		}
		return data;
	}

	public static String loadTestHtml() {
		String html = DemoAuxDataSamples.loadClassPathResourceAsText("testHtmlPage.html");
		return html;
	}

	public static String loadTestText() {
		String html = DemoAuxDataSamples.loadClassPathResourceAsText("testText.txt");
		return html;
	}

	public static String loadClassPathResourceAsText(String resName) {

		try {
			//			URL resource = DemoDataSamples.class.getResource(resName);
			URL resource = DemoAuxDataSamples.class.getClassLoader().getResource(resName);
			InputStream is = resource.openStream();

			StringWriter sw = new StringWriter();

			IOUtils.copy(is, sw);
			is.close();
			return sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String testHtml = loadTestHtml();
		System.out.println(testHtml);
	}

}
