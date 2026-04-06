package ru.tet.java.lang.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;

public class FieldAnnotationDemo {

	@MyAnnotation1(32512.21)
	private double realNumbers;

	public static void main(String[] args) throws NoSuchFieldException {

		Field field = FieldAnnotationDemo.class.getDeclaredField("realNumbers");

		System.out.println(field.getDeclaringClass());
		
		MyAnnotation1[] annotations = field.getAnnotationsByType(MyAnnotation1.class);
		System.out.println(annotations[0].value());
		
//		MyAnnotation1 ann = field.getAnnotation(MyAnnotation1.class);
		MyAnnotation1 ann = field.getDeclaredAnnotation(MyAnnotation1.class);
		
		
		
		System.out.println(ann.value());
		
		
	}

	@Target({ ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	private @interface MyAnnotation1 {
		double value() default 99.9;
	}

}
