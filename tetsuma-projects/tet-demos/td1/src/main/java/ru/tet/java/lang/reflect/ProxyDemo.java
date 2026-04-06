package ru.tet.java.lang.reflect;

import java.lang.reflect.Proxy;

import ru.tet.beans.Person;
import ru.tet.beans.PersonImpl;

public class ProxyDemo {

	public static void main(String[] args) {

		PersonImpl vasia = new PersonImpl("Vasya", 30, "Санкт-Петербург", "Россия");

		ClassLoader vasiaClassLoader = vasia.getClass().getClassLoader();

		Class[] interfaces = vasia.getClass().getInterfaces();

		Person proxyVasia = (Person) Proxy.newProxyInstance(vasiaClassLoader, interfaces,
				new PersonInvocationHandler(vasia));

		proxyVasia.introduce();
		proxyVasia.sayFrom();

	}

}
