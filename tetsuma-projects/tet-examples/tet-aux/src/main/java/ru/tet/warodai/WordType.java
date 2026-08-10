package ru.tet.warodai;

/**
 * Типы слов из словаря Вародай
 * 
 * @author tetsuma
 *
 */
public enum WordType {


	//простейшее слово с одним значением и без доп. выражений
	ONE_TRANSLATION_SIMPLE,

	//слово с одним значением и выражениями типа "~suru" "~o yaru"
	ONE_TRANSLATION_WITH_EXPRESSIONS,

	//слово с одним значением, выражениями и фразами
	ONE_TRANSLATION_WITH_PHRASES,

	//слово с несколькими значениеми, выражениями и фразами
	MULTY_TRANSLATION;

	
}
