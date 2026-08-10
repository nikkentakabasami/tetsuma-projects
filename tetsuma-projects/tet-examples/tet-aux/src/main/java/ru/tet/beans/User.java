package ru.tet.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

	public enum Gender {
		MALE, FEMALE
	};

	@Data
	public static class Name {
		String first, last;
	}

	Gender gender;
	int age;

	Name name = new Name();
	boolean verified;
	byte[] userImage;

	Integer[] keys;

}
