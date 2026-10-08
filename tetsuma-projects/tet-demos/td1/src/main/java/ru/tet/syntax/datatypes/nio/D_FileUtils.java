package ru.tet.syntax.datatypes.nio;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

import ru.tet.aux.DemoAuxDataSamples;
import ru.tet.aux.swing.DemoBase;

public class D_FileUtils extends DemoBase {

	File f1, f2, f3;
	
	
	public void test1() throws Exception {
		/*




		 */

		f1 = Path.of("pom.xml").toFile();
		f2 = Path.of("../pom.xml").toFile();
		
		f3 = Path.of("target/fu_test.txt").toFile();
		
//		FileUtils.touch(f3);
		
		FileUtils.write(f3, DemoAuxDataSamples.sampleStringLyric, StandardCharsets.UTF_8);

		
		
		logEval1(

				FileUtils.getTempDirectory(),
				FileUtils.getUserDirectory(),
				FileUtils.checksumCRC32(f1),
				
				FileUtils.contentEquals(f1,f2),
				FileUtils.byteCountToDisplaySize(12_123_456),
				
				FileUtils.ONE_KB,
				FileUtils.ONE_GB,
				FileUtils.ONE_MB,
				
				FileUtils.sizeOfDirectory(Path.of("target").toFile())
				
				
		);
		
		
		
	}

	public void test2() throws Exception {
		/*
		
		 */


	}

	public void test3() throws Exception {
		/*
		
		 */
	}

	public void test4() throws Exception {
		/*
		
		 */
	}

	@Override
	protected void doInit() throws Exception {
		options().hlComments = false;
	}
	
	public static void main(String[] args) {
		DemoBase.run(D_FileUtils.class);
	}

}
