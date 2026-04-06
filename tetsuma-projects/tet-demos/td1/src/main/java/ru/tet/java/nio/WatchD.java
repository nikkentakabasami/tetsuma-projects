package ru.tet.java.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class WatchD implements Runnable {

	  private final WatchService watchService =
	      FileSystems.getDefault().newWatchService();

	  private final Map<WatchKey, Path> keys = new HashMap<WatchKey, Path>();
	  Path src, dst;

	  WatchD(Path dir, Path dir1) throws IOException {
	    src = dir;
	    dst = dir1;
	    register(src);
	  }

	  private void register(Path dir) throws IOException {
		  
		  
	    Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
	      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
	        WatchKey key = dir.register(watchService,
	            StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
	        keys.put(key, dir);
	        return FileVisitResult.CONTINUE;
	      }
	    });
	    
	    new Thread(this).start();
	    System.out.println("Ready");
	  }

	  public void run() {
	    while (true) {
	      WatchKey key;
	      try {
	        key = watchService.take();
	        System.out.println(key);
	      } catch (InterruptedException x) {
	        return;
	      }
	      Path dir = keys.get(key);
	      if (dir == null) {
	        System.err.println("WatchKey not recognized!!");
	        continue;
	      }

	      for (WatchEvent<?> event : key.pollEvents()) {
	        WatchEvent.Kind kind = event.kind();
	        if (kind == StandardWatchEventKinds.OVERFLOW)
	          continue;
	        WatchEvent<Path> ev = (WatchEvent<Path>) event;
	        Path name = ev.context();
	        Path child = dir.resolve(name);
	        System.out.format("%s: %s\n", event.kind().name(), child);
	        Path r = src.relativize(child);
	        Path to = Paths.get(dst + "/" + r);//, from = Paths.get(src+"/"+r);
	        try {
	          Files.copy(child, to, StandardCopyOption.REPLACE_EXISTING);
	        } catch (Exception e) {
	          Path p1 = Paths.get(src.toString());
	          Path p2 = Paths.get(dst.toString());
	          for (int i = 0; i < r.getNameCount(); i++) {
	            p1 = p1.resolve(r.getName(i));
	            p2 = p2.resolve(r.getName(i));
	            try {
	              Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
	            } catch (Exception e1) {
	              System.out.println("again" + e);
	            }
	          }
	        }
	        if ((kind == StandardWatchEventKinds.ENTRY_CREATE) &&
	            Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) {
	          try {
	            register(child);
	          } catch (IOException x) {
	          }
	        }
	      }
	      boolean valid = key.reset();
	    }

	  }

	  public static void main(String[] args) throws Exception {
	    new WatchD(Paths.get("/home/tetsuma/tmp"), Paths.get("/home/tetsuma/tmp2"));
	  }
	}
