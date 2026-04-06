package ru.tet.java.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import ru.tet.beans.Person;

public class PersonInvocationHandler implements InvocationHandler {

	private Person person;

	public PersonInvocationHandler(Person person) {
		this.person = person;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("Hello!");
		return method.invoke(person, args);
	}
}
