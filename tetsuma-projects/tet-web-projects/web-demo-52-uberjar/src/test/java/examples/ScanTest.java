package examples;

import java.io.IOException;
import java.util.List;

import ru.tet.utils.TetClasspathUtils;

public class ScanTest {
	
	public static void main(String[] args) throws IOException {
		
//		TetIOUtils.scanClasspathFolder("jakarta/");		
		
		String demosPath = "webroot/demos/";
		
		List<String> demoFolders = TetClasspathUtils.scanClasspathFolder(demosPath, (String fileName, boolean isDirectory)->{
			return isDirectory && (fileName.startsWith("demos_") || fileName.equals("templates"));
		});
		
		
		for(String folderName:demoFolders) {
			
			String folderPath = demosPath+folderName;

			List<String> demoPages = TetClasspathUtils.scanClasspathFolder(folderPath, (String fileName, boolean isDirectory)->{
				return !isDirectory && (fileName.endsWith(".jsp") || fileName.endsWith(".html"));
			});
			
			System.out.println("------"+folderPath);
			for(String demoPage:demoPages) {
				System.out.println(demoPage);
			}
			
			
		}
		
		
		
		
	}

}
