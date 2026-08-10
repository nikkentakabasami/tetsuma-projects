package ru.tet.data;

import java.util.ArrayList;
import java.util.List;

import ru.tet.beans.Employee;
import ru.tet.beans.User;
import ru.tet.beans.User.Gender;

public class BeansSamples {


	public static User createTestUserBean() {
		User u = new User();
		u.setGender(Gender.FEMALE);
		u.setAge(23);
		u.getName().setFirst("bob");
		u.getName().setLast("show");
		u.setKeys(new Integer[] {123,528, 951});
		return u;
	}	
	
	
	public static List<Employee> createEmployeeList(){
		
		List<Employee> r = new ArrayList<>();
		
		r.add(new Employee(1, "Gray", 30, "IT"));
		r.add(new Employee(2, "Shiro", 35, "HR"));
		r.add(new Employee(3, "Kuro", 39, "CMM"));
		r.add(new Employee(4, "Kocit", 25, "CMM"));
		r.add(new Employee(5, "Aura", 50, "IT"));
		
		return r;
		
		
	}
	
	
	
}
