package ru.tet.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class Employee implements Serializable {

	int id;
	String firstName;
	String department;

	int age;

	public Employee(int id, String firstName, int age, String department) {
		this.id = id;
		this.firstName = firstName;
		this.age = age;
		this.department = department;
	}

	public Employee() {
	}

}
