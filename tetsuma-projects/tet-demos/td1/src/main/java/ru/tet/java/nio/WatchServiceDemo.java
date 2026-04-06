package ru.tet.java.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceDemo {

	public static void main(String[] args) throws Exception {

		Demo1();

	}

	public static void Demo1() throws Exception {

		WatchService watchService = FileSystems.getDefault().newWatchService();

//      Path path = Paths.get(System.getProperty("user.home"));

		Path tmpDir = Paths.get("/home/tetsuma/tmp");

		tmpDir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				Path file = (Path)event.context();
				System.out.println("Event kind:" + event.kind() + ". File affected: " + file + ".");
			}
			key.reset();
		}

	}

}
