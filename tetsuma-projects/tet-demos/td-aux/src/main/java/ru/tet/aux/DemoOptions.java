package ru.tet.aux;

import ru.tet.sourcebuddy.EvalOptions;

public class DemoOptions {

	//Опции для logEvalString
	public EvalOptions evalStringOptions = new EvalOptions();

	//удалять перед выводом маркерные символы, используемые для задания стилей в логи
	public boolean removeMarkerChars = true;

	//использовать буфер для вывода логов (для ускорения)
	public boolean bufferLogs = true;	
	
}
