package ru.tet.warodai;

import static ru.tet.warodai.WarodaiLineType.LT_EXAMPLE;
import static ru.tet.warodai.WarodaiLineType.LT_REFERENCE;
import static ru.tet.warodai.WarodaiLineType.LT_TRANSLATION;
import static ru.tet.warodai.WarodaiLineType.LT_UNKNOWN;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.MutablePair;

/**
 * Методы для обработки и парсинга слов из вародая.
 * 
 * @author tetsuma
 *
 */
public class WarodaiParseUtils {

	//паттерн номера варианта в начале строки. Наример: "3) "
	public static String RE_VARIANT_NUMBER = "^\\d+[\\)\\.] *";
	public static String RE_LINE_WITH_VARIANT_NUMBER = "^\\d+[\\)\\.].*";
	static Pattern variantNumberPattern = Pattern.compile(RE_VARIANT_NUMBER);
	
	public static String RE_CYRILLIC_PHRAZE = "[ a-zA-Z0-9а-яА-ЯёЁ!?\\-…,.;:—\"\\(\\)\\[\\]]{2,}";
	
	//восклицательный знак
	static String accentString = ""+(char)769;
	
	

	//токены, после которых нужно всё удалить в строке
	static final String[] removeAfterTable = new String[] {
			"<i>см.</i>",
			"<i>ср.</i>",
			"<i>.*см.</i>",
			"(<i>ант.",
			"<i>ант.",
			"<i>ср."
	};

	static final Pattern[] removeAfterPatternsTable = new Pattern[] {
			Pattern.compile("\\(<i>.*см."),
			Pattern.compile("<i>.*см.</i>"),
			Pattern.compile("[a-zA-ZäöüÄÖÜß_ 0-9,.’<>/]+\\(.+\\)$"),
			Pattern.compile("[a-zA-ZäöüÄÖÜß_ 0-9,.’<>/]+$")  //латинская/немецкая хренотень
			
	};
	
	//(<i>слово-основа — тематическая, сюжетная или образная — стихотворения классической поры, см.</i> <a href="#006-09-21">うた【歌】</a>).
	
	static final String[] removeTable = new String[] {
			accentString,
			"<i>связ.:</i> ",
			"<i>",
			"</i>",
			"кн. ",
			"◇",
			//сносим необязательную хирагану (TODO что же с ней делать?) Пример: "(…に, …と)"
			JapanRegExUtils.RE_OPTIONAL_HIRAGANA,
			"\\([\\u3041-\\u3096々\\u30A0-\\u30FF …\\,]+\\)",
			
			//сносим варианты (TODO)
			"\\(тж\\..+\\) ",
			"\\(редко.+\\) ",
			"\\(правильнее.+\\) ",
			"ономат\\.\\:?",
			
			//сносим произношение катаканой (иногда задаётся). Например "/チマタ/"
			"/"+JapanRegExUtils.RE_KATAKANA+"+/"
			
	};
	
	
	static final String[][] replaceTable = new String[][] {
		{"»", "\""},
		{"«", "\""},
		{"чего-л.", "ч.л."},
		{"кем-л.", "к.л."},
		{"что-л", "ч.л."}
	};
	
	/*
	 * 
	 */
	
	/**
	 * Убирает из начала строки номер варианта.
	 * @param s
	 * @return
	 */
	private static String removeVariantNumber(String s) {
		Matcher matcher = variantNumberPattern.matcher(s);
		if (matcher.find()) {
			return s.substring(matcher.end());
		}
		return s;
	}
	
	/**
	 * Извлекает из текущей строки содержимое заданных скобок
	 * 
	 * @param leftBrace
	 * @param rightBrace
	 * @return
	 */
	public static String extractBracesContent(String currLine, char leftBrace, char rightBrace) {
		int ind1 = currLine.indexOf(leftBrace);
		if (ind1 < 0) {
			return null;
		}

		int ind2 = currLine.indexOf(rightBrace);
		if (ind2 < 0) {
			return null;
		}

		return currLine.substring(ind1 + 1, ind2);
	}
	
	
	
	
	/**
	 * Подчищает текущую строку, убирая лишнее, заменяя хираганные блоки на ромадзи
	 * 
	 */
	public static String cleanUpLine(String currLine) {

//		int ind;
		
		//удаляем ненужные блоки (ссылки)
		for (int i = 0; i < removeAfterTable.length; i++) {
			int ind = currLine.indexOf(removeAfterTable[i]);
			
			if (ind >= 0) {
				currLine = currLine.substring(0, ind);
			}
		}
		
		
		for (int i = 0; i < removeAfterPatternsTable.length; i++) {
			Matcher matcher = removeAfterPatternsTable[i].matcher(currLine);
			if (matcher.find()) {
				int ind = matcher.start();
				currLine = currLine.substring(0, ind);
			}
		}
		
		
		//сносим ненужные токены
		for (int i = 0; i < removeTable.length; i++) {
			currLine = currLine.replaceAll(removeTable[i], "");
		}
		
		if (currLine.startsWith("уст.")) {
			return "";
		}
		
		
		//меняем слова и символы
		for (int i = 0; i < replaceTable.length; i++) {
			String[] entry = replaceTable[i];
			currLine = currLine.replaceAll(entry[0], entry[1]);
		}
		

		//по второму кругу (хоть и избыточно)
		for (int i = 0; i < removeAfterPatternsTable.length; i++) {
			Matcher matcher = removeAfterPatternsTable[i].matcher(currLine);
			if (matcher.find()) {
				int ind = matcher.start();
				currLine = currLine.substring(0, ind);
			}
		}
		
		
				
		//убираем номер варианта.
		currLine = removeVariantNumber(currLine);
				
		if (currLine.startsWith(": ")) {
			currLine = currLine.substring(2);
		}
		
		currLine = currLine.trim();

		//меняем на ромадзи предшествующую конструкцию на хирагане
		if (currLine.startsWith("～")) {
			int ind = currLine.indexOf(' ');
			if (ind<0) {
				ind=currLine.length();
			}
			String kana = currLine.substring(1, ind);
			if (JapanRegExUtils.isHiraganaWord(kana)) {
				currLine = "~"+RomanizationUtils.romanizeKanaText(kana)+currLine.substring(ind);
			} else {
				currLine = "~"+kana+currLine.substring(ind);
			}
		}
		
		if (currLine.length()==0) {
			return currLine;
		}

		//убираем точку/семиколон с конца
		char endChar = currLine.charAt(currLine.length()-1);
		if (endChar=='.' || endChar==';') {
			currLine = currLine.substring(0, currLine.length()-1);
		}
		
		return currLine;
	}

		
	/**
	 * Определяет тип строки.
	 * @param currLine
	 * @return
	 */
	public static WarodaiLineType calcLineType(String currLine) {
		
		if (currLine.startsWith("~") 
				|| currLine.matches(RE_CYRILLIC_PHRAZE)
				|| currLine.matches("\\(сокр.+\\).*")
				) {
			return LT_TRANSLATION;
			
			//строка содержит пример использования слова (слово, фразу, несколько предложений) 
		} else if (currLine.matches(JapanRegExUtils.RE_JAP_PHRAZE+" "+RE_CYRILLIC_PHRAZE)) {
			return LT_EXAMPLE;
		} else if (currLine.matches(JapanRegExUtils.RE_JAP_PHRAZE)) {
			return LT_REFERENCE;
		} else {
			return LT_UNKNOWN;
		}		
		
	}	
	
	
	/**
	 * Находит строк с вариантами перевода.
	 * 
	 * @param wordDescLines
	 * @return
	 */
	public static List<MutablePair<Integer, Integer>> findVariantLines(List<String> wordDescLines){
		
//		List<Integer> trVariants = new ArrayList<Integer>(10);
//		int trNumber = 1;

		List<MutablePair<Integer, Integer>> result = new ArrayList<>();
		
		
		MutablePair<Integer, Integer> currentBlock = null;
//		MutablePair<Integer, Integer> currentBlock = new MutablePair<>();
//		currentBlock.setLeft(1);
		
		for (int i = 1; i < wordDescLines.size(); i++) {
			String currLine = wordDescLines.get(i);

			//встретился новый вариант перевода
			if (currLine.matches(RE_LINE_WITH_VARIANT_NUMBER)) {

				//задаём для текущего варианта предел, и сохраняем его
				if (currentBlock!=null) {
					currentBlock.setRight(i);
					
					if (!currentBlock.getLeft().equals(currentBlock.getRight())) {
						result.add(currentBlock);
					}
				}
				
				//создаём новый блок
				currentBlock = new MutablePair<>(i,i+1);

				//если текущая строка - по сути - пустая, значит блок с вариантом начинается со следующей строки
				currLine = removeVariantNumber(currLine);
				if (currLine.length()==0) {
					currentBlock.setLeft(i+1);
				}
				
				
				
				//текущая строка не нумерована
			} else {
				
				if (currentBlock==null) {
					continue;
//					currentBlock = new MutablePair<>(i,null);
				}
				
				//добавляем в текущий блок варианта текущую строку
				currentBlock.setRight(i+1);
			}
		}
		
		if (currentBlock!=null && !currentBlock.getLeft().equals(currentBlock.getRight())) {
			result.add(currentBlock);
		}		
		
		return result;
	}	
	

	public static void printWord(DWordModel w) {
		if (w==null) {
			System.out.println("-");
			System.out.println("");
			return;
		}
		System.out.println(w.getKanaWriting()+"("+w.getKanjiWritings()+")  \""+w.getPronunciation()+"\"");
		System.out.println(w.hasTranslation()?w.getTranslation():"-");
		
		if (w.getExamples()!=null) {
			System.out.print("examples: ");
			for(DWordExampleModel ex:w.getExamples()) {
				System.out.print(ex.getExample()+"; ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
		
	
	
}
