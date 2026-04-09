package ru.tet.sourcebuddy;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EvalOptions {

	//класс, который будет шаблоном для вычисления выражений
	//может содеражать необходимые дополнительные поля, import-ы
	Class<?> templateClass = null;
	
	//классы, которые нужно будет добавить в секцию import
	List<Class<?>> importClasses = new ArrayList<>();
	
}
