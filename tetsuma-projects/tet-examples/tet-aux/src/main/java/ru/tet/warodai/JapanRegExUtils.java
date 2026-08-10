package ru.tet.warodai;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * разные методы для анализа слов через регулярные выражения
 * 
 * @author tetsuma
 *
 */
public class JapanRegExUtils {

	
	public static final String RE_KANJI = "[\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A]";
	public static final String RE_HIRAGANA = "[\\u3041-\\u3096々]";
	public static final String RE_KATAKANA = "[\\u30A0-\\u30FF]";
	public static final String RE_KANA = "[\\u3041-\\u3096々\\u30A0-\\u30FF]";

	public static final String RE_KANJI_HIRAGANA_WORD = "[\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A\\u3041-\\u3096々]+";
	public static final String RE_JAP_WORD = "[\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A\\u3041-\\u3096\\u30A0-\\u30FF々]+";
	
	public static final String RE_JAP_PHRAZE = "A?[ B\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uF900-\\uFA6A\\u3041-\\u3096\\u30A0-\\u30FF…～々？!！『』―,、\\[\\]\\(\\)]{3,}";

	public static final String RE_OPTIONAL_HIRAGANA = "\\("+JapanRegExUtils.RE_KANA+"+\\)";
	
	// 
	
	
//	public static final String RE_KANJI_HIRAGANA_WORD = RE_HIRAGANA+"*"+RE_KANJI+"+"+RE_HIRAGANA+"*";
	
	
	public static Pattern kanjiPattern = Pattern.compile(RE_KANJI);
	public static Pattern japWordPattern = Pattern.compile(RE_JAP_WORD);
	
	public static Pattern firstKanaPattern = Pattern.compile("^"+RE_KANA+"+");
	public static Pattern nextKanaPattern = Pattern.compile("(?<=[,･] ?)"+RE_KANA+"+");
	
	/**
	 * возвращает найденные слова, написанные каной (через запятую)
	 * 
	 * @param s
	 * @return
	 */
	public static String findKanaWord(String s) {
		Matcher matcher = firstKanaPattern.matcher(s);
		
		StringBuilder result = new StringBuilder();
		if (matcher.find()) {
			result.append(matcher.group());
		}
		
		matcher = nextKanaPattern.matcher(s);
		while (matcher.find()) {
			result.append(',');
			result.append(matcher.group());
		}
		return result.toString();
	}
	
	
	/**
	 * слово содержащее кандзи (и возможно хирагану)
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isKanjiHiraganaWord(String word) {
		
		//нет кандзи
		if (!kanjiPattern.matcher(word).find()) {
			return false;
		}
		
		return word.matches(RE_KANJI_HIRAGANA_WORD);
	}

	/**
	 * слово, содержащее только хирагану.
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isHiraganaWord(String word) {
		return word.matches(RE_HIRAGANA+"+");
	}
	
	/**
	 * слово, содержащее только катакану.
	 * 
	 * @param word
	 * @return
	 */
	public static boolean isKatakanaWord(String word) {
		return word.matches(RE_KATAKANA+"+");
	}
	

	/**
	 * извлекает из слова неповторяющиеся кандзи
	 * 
	 * @param word
	 * @return
	 */
	public static String findKanjis(String word) {
		
		String result = new String();
		Matcher matcher = kanjiPattern.matcher(word);
		while(matcher.find()) {
			String s = matcher.group();
			if (!result.contains(s)) {
				result = result+s;
			}
		}
		return result;
		
	}
	
	
	
	/**
	 * Ищет в заданной строке кандзи - возвращает индекс первого найденного
	 * 
	 * @param string
	 * @return
	 */
	public static int findKanji(String string) {
		Matcher matcher = kanjiPattern.matcher(string);
		if (matcher.find()) {
			return matcher.start();
		}
		return -1;
		
	}	
	
	
	
	
	public static void main(String[] args) {
		
		System.out.println(isKanjiHiraganaWord("挟み込む"));
		System.out.println(isKanjiHiraganaWord("挟み"));
		System.out.println(isKanjiHiraganaWord("おいそれと"));
		
		System.out.println(isKatakanaWord("リップ"));
		System.out.println(isKatakanaWord("リップ鱻lip"));
		
		System.out.println(findKanjis("挟み込む"));
		
		System.out.println(findKanji("このあとの展開は目に見える"));
		
		
	}	
	

	
	
	
	
}
