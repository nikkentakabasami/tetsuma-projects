package ru.tet.java.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;

public class FileVisitorDemo {

	static class MyFileVisitor extends SimpleFileVisitor<Path> {

		
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			System.out.println("preVisitDirectory: "+dir.getFileName());
			return super.preVisitDirectory(dir, attrs);
		}
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

			System.out.println(file.getFileName());

			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			System.out.println("postVisitDirectory: "+dir.getFileName());
			return super.postVisitDirectory(dir, exc);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		Demo1();
		
	}
	
	
	public static void Demo1() throws Exception {

		Path tmpDir = Paths.get("/home/tetsuma/tmp");
		MyFileVisitor visitor = new MyFileVisitor();
		
		
		Files.walkFileTree(tmpDir, visitor);
//		Files.walkFileTree(tmpDir, Collections.EMPTY_SET, 2, visitor);
		
	}
	
	
	
	
	
	

}
