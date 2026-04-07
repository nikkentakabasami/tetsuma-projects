package ru.tet.aux;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Этой аннотацией помечаются дополнительные методы, привязанные к какому либо тесту.
 * value - номер теста
 * 
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AuxTest {
	int value() default 1;
}
