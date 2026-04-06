package ru.tet.beans;

public class PersonImpl implements Person {

	private String name;
	private int age;
	private String city;
	private String country;

	public PersonImpl() {
	}
	
	public PersonImpl(String name, int age, String city, String country) {
		this.name = name;
		this.age = age;
		this.city = city;
		this.country = country;
	}

	@BuilderProperty
	public void setName(String name) {
		this.name = name;
	}
	
	@BuilderProperty
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void introduce() {
		System.out.println("Меня зовут " + this.name);
	}

	public void sayAge() {
		System.out.println("Мне " + this.age + " years");
	}

	public void sayFrom() {
		System.out.println("Я из города " + this.city + ", " + this.country);
	}

}