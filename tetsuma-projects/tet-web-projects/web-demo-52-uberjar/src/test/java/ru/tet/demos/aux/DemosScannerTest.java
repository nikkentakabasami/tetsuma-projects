package ru.tet.demos.aux;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ru.tet.beans.DemoFolder;
import ru.tet.beans.DemoPage;

public class DemosScannerTest {

	@Test
	@Disabled
	public void test1() throws IOException {

		List<DemoFolder> dfs = DemosScanner.getInstance().scanDemos();
		
		for(DemoFolder df:dfs) {
			System.out.println(df.getName());
			if (df.getDesc()!=null) {
				System.out.println(df.getDesc());
			}
			
			for(DemoPage page:df.getPages()) {
				System.out.println("  "+page.getName());
				if (page.getDesc()!=null) {
					System.out.println("    "+page.getDesc());
				}
			}
		}
		
	}
	
	@Test
	@Disabled
	public void test2() throws IOException {

		String title = DemosScanner.getInstance().scanDemoFileDesc(DemosScanner.DEMOS_PATH + "demos_jquery/410_jquery_selectors1.html");
		System.out.println(title);
		
	}
	
	@Test
	public void test3() throws IOException {

		String[] siblingPages = DemosScanner.getInstance().findSiblingPages("410_jquery_selectors1.html");
		System.out.println(Arrays.toString(siblingPages));
		
	}
	
	
	
	
}
