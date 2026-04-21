package ru.tet.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TetIOUtils {

	/**
	 * Загружает classpath-ресурс в виде строки.
	 * 
	 * @param resourceName
	 * @return
	 * @throws IOException
	 */
	public static String loadResourceAsString(String resourceName) throws IOException {
		try (final var is = TetIOUtils.class.getClassLoader().getResourceAsStream(resourceName)) {
			return new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
		}
	}

	
	/**
	 * Преобразовать поток в список строк.
	 * Аналог IOUtils.readLines
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static List<String> streamToLines(InputStream is) throws IOException {

		List<String> result = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = reader.readLine()) != null) {
				result.add(line);
			}
		}
		return result;
	}

	/**
	 * Преобразовать поток в строку.
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String streamToString(InputStream is) throws IOException {
		return new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
	}

}
