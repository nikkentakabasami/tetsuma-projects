package ru.tet.demos;

import ru.tet.sourcebuddy.EvalOptions;

public class DemoOptions {

	//Опции для logEvalString
	public EvalOptions evalStringOptions = new EvalOptions();

	//удалять перед выводом маркерные символы, используемые для задания стилей в логи
	public boolean removeMarkerChars = true;

	//использовать буфер для вывода логов (для ускорения)
	public boolean bufferLogs = true;	

	//добавлять кнопки-тестов
	//в них нет нужды, ведь тесты можно выполнить через Ctrl+1,2,3
	public boolean addTestButtons = false;	
	
	//максимальное число строк/значений для вывода
	public int maxEntries = 10;

	public boolean logSources = true;	
	
	
}
