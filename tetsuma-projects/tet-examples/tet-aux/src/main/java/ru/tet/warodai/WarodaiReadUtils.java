package ru.tet.warodai;

import static ru.tet.warodai.WarodaiParseUtils.calcLineType;
import static ru.tet.warodai.WarodaiParseUtils.cleanUpLine;
import static ru.tet.warodai.WarodaiParseUtils.extractBracesContent;
import static ru.tet.warodai.WarodaiParseUtils.findVariantLines;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;

import ru.tet.data.WarodaiDictionaryReader;

/**
 * вспомогательные методы для парсинга словаря Вародай.
 * 
 * @author tetsuma
 *
 */
public class WarodaiReadUtils {

	
	
	
	
	/**
	 * Считывание данных из первой строки, описывающей слово
	 * 
	 * @param currWord
	 * @param line
	 */
	public static DWordModel parseKeyWordLine(String currLine) {
		
		//непонятно как разбираться с этими вариантами - просто снесём пока
		currLine = currLine.replaceAll("I", "").replaceAll("…", "");
		
		String code = extractBracesContent(currLine,'〔', '〕');
		if (code==null) {
			throw new RuntimeException("wrong new word line:"+currLine);
		}

		String kanji = extractBracesContent(currLine,'【', '】');
		if (kanji==null) {
			kanji = "";
		}
		kanji = kanji.replace('･', ',');
		String[] kanjiWritings = kanji.split(",");
		
		//TODO может быть несколько записей каной
		String kanaWriting = JapanRegExUtils.findKanaWord(currLine);
		
		String pron = RomanizationUtils.romanizeKanaText(kanaWriting);
		
		int flags = 2;
		if (JapanRegExUtils.isKatakanaWord(kanaWriting)) {
			flags = 4;
		} else if (kanji.length()>0) {
			flags = 1;
		}
		
		
		DWordModel currWord = new DWordModel();
		currWord.setCode(code);
		currWord.setKanjiWritings(kanji);
		currWord.setKanjiWritingsCount(kanjiWritings.length);
		currWord.setKanaWriting(kanaWriting);
		currWord.setPronunciation(pron);
		currWord.setFlags(flags);
		
		
		for (int i = 0; i < kanjiWritings.length; i++) {
			String s = kanjiWritings[i];
			if (StringUtils.isBlank(s)) {
				continue;
			}
			currWord.getWritingsList().add(new DWordWritingModel(s));
		}
		
		return currWord;
	}
	
	
//	(<i>сокр.</i> 医学博士) доктор медицины.
//	Pattern reduction = Pattern.compile("");
	
	public static DWordModel currWord;
	
	/**
	 * считывает строки с переводом, заполняет модели полученными данными
	 * 
	 * @param wordDescLines
	 * @param currWord
	 */
	public static DWordModel parseWord(List<String> wordDescLines) {
		
		currWord = parseKeyWordLine(wordDescLines.get(0));

		//находим варианты перевода и их расположение
		List<MutablePair<Integer, Integer>> trVariants = findVariantLines(wordDescLines);
		
		//чистим строки, убирая мусор
		for (int i = 1; i < wordDescLines.size(); i++) {
			String currLine = wordDescLines.get(i);
			currLine = cleanUpLine(currLine);
			
			wordDescLines.set(i, currLine);
		}
		
		//простейший вариант - у слова один перевод
		if (trVariants.size()==0) {
			
			currWord.setWordType(WordType.ONE_TRANSLATION_SIMPLE);
			currWord.setTranslationCount(1);
			
			readTranslationVariantBlock(wordDescLines, 1, wordDescLines.size(), 0, currWord);
			
			
			
		} else {
			//многозначное слово
			currWord.setWordType(WordType.MULTY_TRANSLATION);
			currWord.setTranslationCount(trVariants.size());

			//такое тоже бывает: 004-30-47
//			if (trVariants.size()<2) {
//				System.out.println("word with one variant?:"+currWord.getCode());
//			}
			
			//смотрим строки до вариантов перевода
			for (int i = 1; i < trVariants.get(0).getLeft(); i++) {
				String currLine = wordDescLines.get(i);
				
				if (currLine.length()==0) {
					continue;
				}
				currWord.addTranslation(currLine+" ");
			}

			
			//смотрим варианты перевода
			for (int varInd = 0; varInd < trVariants.size(); varInd++) {
				MutablePair<Integer, Integer> variantBlock = trVariants.get(varInd);
				
				int varNumber = varInd+1;
				
				readTranslationVariantBlock(wordDescLines, variantBlock.getLeft(), variantBlock.getRight(), varNumber, currWord);
				
			}//for vars
		}
		return currWord;
	}
	


	

	/**
	 * Считываем один вариант перевода из блока с описанием слова и записывает его в модель.
	 * 
	 * @param wordDescLines
	 * @param trVariantRowInd
	 * @param trVariantEndRowInd
	 * @param currWord
	 */
	public static void readTranslationVariantBlock(
			List<String> wordDescLines, 
			int trVariantRowInd, 
			int trVariantEndRowInd,
			int variantNumber,
			DWordModel currWord) {

		
		if (variantNumber>0) {
			currWord.appendToTranslation(variantNumber+") ");
		}
		
		for (int i = trVariantRowInd; i < trVariantEndRowInd; i++) {
			String currLine = wordDescLines.get(i);

			//убираем номер варианта. Например "5) "
//			if (i == trVariantRowInd) {
//				currLine = currLine.substring(varInd<9?3:4);
//			}
			//чистим линию
//			currLine = cleanUpLine(currLine);
			
			if (currLine.length()==0) {
				continue;
			}
			
			//извлекаем из линии выжные пометки, которые нужно будет потом сохранить
			currLine = extractImportantNote(currLine);
			
			WarodaiLineType lineType = calcLineType(currLine);
			switch (lineType) {
			//строка с переводом
			case LT_TRANSLATION:
				currWord.appendToTranslation(currLine+importantNote+"; ");
				break;
				//строка содержит пример использования слова (слово, фразу, несколько предложений) 
			case LT_EXAMPLE:
				currWord.addExample(currLine);
				break;
				//ссылка на другое слово
			case LT_REFERENCE:
				break;
			case LT_UNKNOWN:
				System.out.println("unknown line type: ("+WarodaiDictionaryReader.getInstance().getCurrentLineNo()+") "+currLine+" !"+currWord.getCode());
				break;
			default:
				break;
			}
			
		}//for variant row			
		
	}
	
	
	static final Pattern[] importantNotePatterns = new Pattern[] {
			Pattern.compile("\\(тк\\..+\\)"),
			Pattern.compile("\\(тж\\..+\\)"),
			Pattern.compile("\\(обычно.+\\)")
	};
	//
	
	private static String importantNote;
	
	/**
	 * Извлекает из заданной линии важную пометку (используемое написание, альтернативное написание) и сохраняет её в importantNote
	 * 
	 * @param currLine
	 * @return
	 */
	public static String extractImportantNote(String currLine) {
		
		StringBuilder note = new StringBuilder();
		for (int i = 0; i < importantNotePatterns.length; i++) {
			Matcher matcher = importantNotePatterns[i].matcher(currLine);
			if (matcher.find()) {
				note.append(currLine.substring(matcher.start(), matcher.end()));
				currLine = currLine.substring(0, matcher.start())+currLine.substring(matcher.end());
			}
		}
		importantNote = note.toString();
		return currLine;
	}
	
	
	
}
