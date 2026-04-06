package ru.tet.java.util;

import java.util.prefs.Preferences;

public class PreferencesDemo {

	public static void main(String[] args) {
		prefDemo1();
	}
	
	
	public static void prefDemo1() {

		Preferences prefs = Preferences.userRoot().node(PreferencesDemo.class.getName());
		    String ID1 = "Test1";
		    String ID2 = "Test2";
		    String ID3 = "Test3";

		    System.out.println(prefs.getBoolean(ID1, true));
		    System.out.println(prefs.get(ID2, "Hello World"));
		    System.out.println(prefs.getInt(ID3, 50));

		    prefs.putBoolean(ID1, false);
		    prefs.put(ID2, "Hello Europa");
		    prefs.putInt(ID3, 45);

		    prefs.remove(ID1);
		
		
		
	}
	
	
}
