package ru.tet.beans;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Для тестирования JAXB
 * 
 * @author tetsuma
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {
	@XmlAttribute
	private int id;
	@XmlElement(namespace = "http://packt.jee.eclipse.jaxb.example")
	private String name;
	private int credits;
	@XmlElement(name = "course_teacher")
	private Teacher teacher;

	public Course() {
	}

	public Course(int id, String name, int credits) {
		this.id = id;
		this.name = name;
		this.credits = credits;
	}
	
	public static Course makeSampleBean() {
		return makeSampleBean(1);
	}

	public static Course makeSampleBean(int id) {
		Course course = new Course(id, "Course-"+id, 5);
		course.setTeacher(new Teacher(1, "Teacher-1"));
		return course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
