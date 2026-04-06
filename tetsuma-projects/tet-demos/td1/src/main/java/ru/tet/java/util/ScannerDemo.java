package ru.tet.java.util;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ScannerDemo {

	public static void main(String[] args) throws IOException {
		
//		demo1();
//		demo3_findInLine();
//		demo4_findWithinHorizon();
		demo5();
		
	}
	
	public static void demo1() throws IOException {

		URL testRes = ScannerDemo.class.getResource("/templates/journal2/gen_dto.st");
		
		try(Scanner scanner = new Scanner(testRes.openStream())){
			 while(scanner.hasNext()) {
				 String token = scanner.next();
				 System.out.println(token);
			 }
		}
		
		 
	}

	public static void demo2() throws IOException {
		
		URL testRes = ScannerDemo.class.getResource("/templates/journal2/gen_dto.st");
		
		try(Scanner scanner = new Scanner(testRes.openStream())){
			 while(scanner.hasNextLine()) {
				 String token = scanner.nextLine();
				 System.out.println(token);
			 }
		}
	}
	
	public static void demo3_findInLine() throws IOException {
		
		URL testRes = ScannerDemo.class.getResource("/templates/journal2/gen_dto.st");
		
		try(Scanner scanner = new Scanner(testRes.openStream())){
			
			int cnt=0;
			 while(scanner.hasNextLine()) {
				 String token = scanner.nextLine();
				 System.out.println(cnt++);
					String s = scanner.findInLine("<.+?>");
					while (s!=null) {
						System.out.println(s);
						s = scanner.findInLine("<.+?>");
					}
				 
			 }
			
		}
	}
	
	
	public static void demo4_findWithinHorizon() throws IOException {
		
		URL testRes = ScannerDemo.class.getResource("/templates/journal2/gen_dto.st");
		
		try(Scanner scanner = new Scanner(testRes.openStream())){
			
			String s;
			do {
				s = scanner.findWithinHorizon("<.+?>", 1000);
				if (s!=null)
					System.out.println(s);
			} while (s!=null);
			
		}
	}	

	
	public static void demo5() throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your age: ");
		int age = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter your first name: ");
		String firstName = scanner.nextLine();		
		
		System.out.format("%s - %d", firstName, age); 
		
	}
	
	
	
}
