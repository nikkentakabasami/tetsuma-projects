package aux;

import java.io.File;
import java.util.Arrays;


/**
 * Для генерации линков страницы index.html
 */
public class GenerateLinks {
	
	
	public static void main(String[] args) {
		
		
		String folderName = "src/main/resources/accord-lib/accord/demos";
		String links = generateLinks(folderName);		
		System.out.println(links);
		
		
	}

	
	static String generateLinks(String folderName) {
		
		File folder = new File(folderName);
		
		String[] files = folder.list();
		
		Arrays.sort(files);

		StringBuilder result = new StringBuilder();
		for (String fn : files) {
			
			if (!fn.endsWith(".html")) {
				continue;
			}
	
			
			String anchor = String.format("<a href=\"%s\" target=\"acc\">%s</a>\n", fn,fn);
			
			result.append(anchor);
			
//			System.out.println(anchor);
			
		}
		
		return result.toString();
		
	}
	
	
	
}
