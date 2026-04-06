package ru.tet.java.nio;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class PathDemo {

	
	
	
	public static void main(String[] args) {
		testIterator();
	}
	
	
	public static void testIterator() {
		
		Path myFilesPath = Paths.get("/home/tetsuma/myfiles/");
		
		
		
		Iterator<Path> iterator = myFilesPath.iterator();
		
		while (iterator.hasNext()) {
			Path subfile = iterator.next();
			System.out.println(subfile.getFileName());
		}
		
		
	}
	
	
	
	
}
