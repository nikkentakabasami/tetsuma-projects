package ru.tet.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Задаётся на orm-классы, для которых нужно сгенерировать исходники для журнала
 * В качестве параметра
 * 
 * @author tetsuma
 *
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.SOURCE )
public @interface SUJournal {
	String dtoFullClassName();
	
	//папка с ресурсами (в класспазе)
	String templatesFolder();
}