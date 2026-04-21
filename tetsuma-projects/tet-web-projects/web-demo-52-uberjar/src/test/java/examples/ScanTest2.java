package examples;

import java.io.IOException;
import java.util.List;

import ru.tet.utils.TetClasspathUtils;

public class ScanTest2 {
	
	public static void main(String[] args) throws IOException {
		
		
//		String demosPath = "jakarta/";
		String demosPath = "jakarta/servlet/";
		
		
		List<String> demoFolders = TetClasspathUtils.scanClasspathFolder(demosPath, (String fileName, boolean isDirectory)->{
			return isDirectory;
//			return isDirectory && (fileName.startsWith("demos_") || fileName.equals("templates"));
		});
		
		
		for(String folderName:demoFolders) {
			
			String folderPath = demosPath+folderName;
			System.out.println(folderPath);
			
		}
		
		
		
		
	}

}
