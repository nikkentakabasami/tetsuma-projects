package ru.tet.data;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ru.tet.warodai.DWordModel;
import ru.tet.warodai.WarodaiReadUtils;

/**
 * Класс для загрузки данных из текстового файла со словарём Вародай.
 * Тут используется для получения тестовых данных для демок.
 *  
 * 
 */
public class WarodaiDictionaryReader {

	private static WarodaiDictionaryReader instance;

	public WarodaiDictionaryReader() {
		instance = this;
	}

	public static WarodaiDictionaryReader getInstance() {
		return instance;
	}

	/**
	 * содержит строки, описывающее текущее слово (перевод, фразы, конструкции)
	 */
	List<String> wordLines = new ArrayList<String>(40);

	//обрабатываемая в данный момент строка
	String currLine;

	//её номер
	int currentLineNo = 0;

	Map<String,DWordModel> wordsMap;
	
	
	public int getCurrentLineNo() {
		return currentLineNo;
	}

	
	
	/**
	 * Парсит wordLines в слово, сохраняет его в БД, очищает wordLines
	 * 
	 * @return
	 * @throws Exception
	 */
	private void parseAndSaveCurrentWord() throws Exception {

		if (wordLines.size() <= 1) {
			return;
		}

		DWordModel currentWord = WarodaiReadUtils.parseWord(wordLines);
		if (currentWord == null) {
			return;
		}
		wordLines.clear();

		if (!currentWord.hasTranslation() && !currentWord.hasExamples()) {
			return;
		}

		System.out.println(currentWord.getCode()+" "+currentWord.getPronunciation());
		wordsMap.put(currentWord.getCode(), currentWord);

	}

	/**
	 * Считывание данных словаря из файла и запись их в БД 
	 * 
	 * @param startRow Начало диапазона считываемых строк (default 4)
	 * @param endRow Конец диапазона считываемых строк (default 0)
	 * @throws Exception
	 */
	public Map<String,DWordModel> readDictionary(final int startRow, final int endRow) throws Exception {
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("demo_data/warodai_fragment.txt");
		
		readDictionary(0, 0, is);
		
		return wordsMap;
		
	}

	/**
	 * Считывание данных словаря из файла и запись их в БД 
	 * 
	 * @param startRow Начало диапазона считываемых строк (default 4)
	 * @param endRow Конец диапазона считываемых строк (default 0)
	 * @param fileName
	 * @throws Exception
	 */
	public void readDictionary(final int startRow, final int endRow, final InputStream fileInputStream) throws Exception {

		wordsMap = new HashMap<>();
		
		FileInputStream inputStream = null;
		Scanner sc = null;
		currentLineNo = 0;
		try {
			
			
			sc = new Scanner(fileInputStream, "UTF-16");
			while (sc.hasNextLine()) {
				currLine = sc.nextLine();

				//пропускаем первые ненужные строки (может использоваться при отладке чтобы начать сканирования с нужной записи)
				if (currentLineNo < startRow && currentLineNo < 4) {
					currentLineNo++;
					continue;
				}

				//Используется при сканировании в заданном диапазоне строк
				if (endRow > 0 && currentLineNo > endRow) {
					break;
				}

				//Вывод прогресса загрузки в консоль
				if (currentLineNo % 500 == 0) {
					System.out.println(currentLineNo);
				}

				//конец блока с описанием слова - сохраняем текущее слово
				if (currLine.length() == 0) {
					parseAndSaveCurrentWord();
					continue;
				}

				//если считываем не с начала - прокручиваем до ближайшего слова
				if (wordLines.size() == 0 && currLine.indexOf('〔') < 0) {
					continue;
				}

				//добавляем строку в список
				wordLines.add(currLine);

				currentLineNo++;
			}

			//Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} catch (Exception e) {
			System.out.println("error in line " + currentLineNo + ":" + (wordLines.size() > 0 ? wordLines.get(0) : ""));
			throw e;
		}

		finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}

		parseAndSaveCurrentWord();

	}

	public static void main(String[] args) throws Exception {
		WarodaiDictionaryReader reader = new WarodaiDictionaryReader();
		Map<String, DWordModel> dictionary = reader.readDictionary(0, 0);
		System.out.println(dictionary.size());
		
	}

}
