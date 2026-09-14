package ru.tet.aux;

import java.util.ArrayList;
import java.util.List;

import ru.tet.beans.Employee;
import ru.tet.beans.PersonImpl;
import ru.tet.beans.User;
import ru.tet.beans.User.Gender;

/**
 * Образцы бинов для тестов.
 */
public class DemoAuxObjectSamples {
	
	public static PersonImpl createPerson1() {
		PersonImpl vasia = new PersonImpl("Vasya", 30, "Санкт-Петербург", "Россия");
		return vasia;
	}
	

	public static User createTestUserBean() {
		User u = new User();
		u.setGender(Gender.FEMALE);
		u.setAge(23);
		u.getName().setFirst("bob");
		u.getName().setLast("show");
		u.setKeys(new Integer[] {123,528, 951});
		return u;
	}	
	

	public static Employee createEmployee1() {
		return new Employee(1, "Gray", 30, "IT");
	}
	public static Employee createEmployee2() {
		return new Employee(2, "Shiro", 35, "HR");
	}
	public static Employee createEmployee3() {
		return new Employee(3, "Kuro", 39, "CMM");
	}
	public static Employee createEmployee4() {
		return new Employee(4, "Kocit", 25, "CMM");
	}

	
	
	
	public static List<Employee> createEmployeeList(){
		
		List<Employee> r = new ArrayList<>();
		
		r.add(createEmployee1());
		r.add(createEmployee2());
		r.add(createEmployee3());
		r.add(createEmployee4());
		r.add(new Employee(5, "Aura", 50, "IT"));
		
		return r;
		
		
	}	
	
	
	
	
	
}
