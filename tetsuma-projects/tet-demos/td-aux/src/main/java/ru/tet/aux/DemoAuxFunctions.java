package ru.tet.aux;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.IntStream;

/**
 * Разные вспомогательные функции для демок
 */
public class DemoAuxFunctions {

	public static Path createTestFile(String fileName, String content) throws IOException {
		Path p = Path.of("target", fileName);
		if (Files.notExists(p)) {
			Files.createFile(p);
			Files.writeString(p, content);
		}
		return p;
	}


	//Создаёт текстовый файл, содержащий таблицу с числами 
	public static Path createNumbersTableTextFile(String fileName) throws IOException {
		Path p1 = Paths.get("target", fileName);
		if (Files.exists(p1)) {
			return p1;
		}

		byte[] content =
				IntStream.range(0, 20)
						.mapToObj(i -> {
							String sep = i % 5 != 0 ? ", " : System.lineSeparator();
							return i + sep;
						})
						.reduce("", String::concat)
						.getBytes(StandardCharsets.UTF_8);
		Files.write(p1, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		return p1;
	}	
	
	public static Path getTestTextFile() throws IOException {
		return createTestFile("testFile1.txt",DemoAuxDataSamples.sampleStringLyric);
	}

	
	
}
