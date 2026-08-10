package ru.tet.warodai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Слово из словаря Вародай. слово+чтение+значения
 * 
 * @author tetsuma
 *
 */
@Data
@NoArgsConstructor
public class DWordModel implements Serializable {

	Integer id;

	//код слова в словаре Вародай
	String code;

	//запись каной (обычно хирагана, только для полностью катаканных слов - катаканой)
	String kanaWriting;

	//произношение на ромадзи (может быть несколько, через запятую)
	String pronunciation;

	//варианты написания с использование кандзи, разделённые запятой (в порядке популярности)
	String kanjiWritings;

	//самый популярный/первый вариант написания на кандзи (пока не заполнен!)
	String kanjiWriting;

	//сколько всего вариантов написания через кандзи
	int kanjiWritingsCount;

	//основные варианты перевода (до трёх, через семиколон)
	String translation;

	//всего вариантов перевода
	int translationCount;

	//номер по порядку по популярности (0-неизвестен) 
	int frequencyRating;

	//разнообразные флаги
	//1-содержит кандзи, 2-чистая хирагана, 4-чистая катакана
	int flags;

	//тип слова (по сложности)
	WordType wordType;

	//тип слова (как часть речи) (пока не заполнен!)
	WordLexicalType lexicalType;

	//	@Transient
	List<DWordExampleModel> examples = new ArrayList<>();

	List<DWordWritingModel> writingsList = new ArrayList<>();

	public boolean hasTranslation() {
		return !StringUtils.isBlank(translation);
	}

	public boolean hasExamples() {
		return examples.size() > 0;
	}

	public void addTranslation(String additionalTranslation) {
		if (translation == null) {
			translation = additionalTranslation;
		} else {
			translation = translation + "; " + additionalTranslation;
		}
	}

	public void appendToTranslation(String s) {
		if (translation == null) {
			translation = s;
		} else {
			translation = translation + s;
		}
	}

	public void addExample(String s) {
		DWordExampleModel ex = new DWordExampleModel();
		ex.setTranslationId(id);
		ex.setExample(s);
		examples.add(ex);
	}

}
