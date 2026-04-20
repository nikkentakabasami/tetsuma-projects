package ru.tet.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DemoFolder {

	String name;
	
	String desc;
	
	List<String> pages;
	
}
