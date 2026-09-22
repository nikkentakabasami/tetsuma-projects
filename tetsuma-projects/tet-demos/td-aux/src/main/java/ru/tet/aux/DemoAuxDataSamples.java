package ru.tet.aux;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.io.IOUtils;

import ru.tet.beans.SuIdNameModel;

/**
 * Образцы данных для демок.
 */
public class DemoAuxDataSamples {

	public static String sampleString = "Съешь ещё этих мягких французских булок, да выпей же чаю";
	
	public static String sampleStringShort = "fujori dake no kishoutenketsu";
	public static String sampleStringLyric = "Houritsu mo chitsujo demo  kurutta yatsu ga tsukutteru, Masa ni FARUSU  fujouri dake no kishou tenketsu";
	public static String sampleStringJap = "法律も秩序でも　狂った奴が創ってる まさに ファルス 不条理だけの起承転結";
	 
	//Строка с токенами, для тестирования StreamTokenizer
	public static String sampleStringTokenized = "(;GM[1.4] /comment\n  один-четыре\t分け前 \n\"quo ted1\" \n 'quoted 2' FF[4] CA[UTF-8]AP[CGoban:3]KM[0.50]TM[3600]";	
	
	public static String  multilineTestString = "привет //𝄞\nКак дела?\n2+6/5";

	public static String jsonString1 = "{\"enplantId\":121,\"enplantGuid\":\"D057056E385B8F72E050007F01014BBE\",\"reportId\":\"reportAlpha\",\"filterParams\":{\"параметр1\": \"因果\", \"параметр2\" : \"что то на русском\"},\"token\":\"someToken\"}";
	

	//url для тестирования get-запросов
	public static String url_get_example = "https://example.com";
	public static String url_get_httpbin = "https://httpbin.org/post";
	
	//url для тестирования post-запросов
	public static String url_post_httpbin = "https://httpbin.org/post";
	
	public static String[] tableHeadings1 = { "From", "Address", "Subject", "Size" };

	public static Object[][] tableData1 = { { "Wendy", "Wendy@HerbSchildt.com", "Hello Herb", 287 },
			{ "Alex", "Alex@HerbSchildt.com", "Check this out!", 308 },
			{ "Hale", "Hale@HerbSchildt.com", "Found a bug", 887 },
			{ "Todd", "Todd@HerbSchildt.com", "Did you see this?", 223 },
			{ "Steve", "Steve@HerbSchildt.com", "I'm back", 357 },
			{ "Ken", "Ken@HerbSchildt.com", "Arrival time change", 512 } };

	public static String[] tableHeadings2 = { "String", "Integer", "Boolean" };

	public static Object[][] tableData2 = { { "aaa", 12, true }, { "bbb", 5, false }, { "CCC", 92, true },
			{ "DDD", 0, false } };

	
	public static String[] tableHeadings3 = { "propName", "propValue" };

	public static Object[][] tableData3 = { 
			{ "Wendy", "Wendy@HerbSchildt.com"},
			{ "Alex", "Alex@HerbSchildt.com"},
			{ "Hale", "Hale@HerbSchildt.com"}};
	
	
	public static String apples[] = { "Winesap", "Cortland", "Red Delicious", "Golden Delicious", "Gala", "Fuji",
			"Granny Smith", "Jonathan" };

	List<Integer> numbersList = IntStream.range(1, 10).boxed().toList();
	
	//расширенный список яблок
	public static List<String> makeApplesList(int size) {
		List<String> data = new ArrayList<>();
		
		for (int i = 0; i < apples.length; i++) {
			String apple = apples[i];
			
			for (int j = 0; j < size; j++) {
				long rand = Math.round(Math.random()*100);
				String val = apple+rand;
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
