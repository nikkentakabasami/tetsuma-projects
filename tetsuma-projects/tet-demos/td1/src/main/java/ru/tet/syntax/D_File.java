package ru.tet.syntax;

import java.io.File;
import java.nio.file.Paths;

import ru.tet.aux.swing.DemoBase;

//Шаблон для создания новых демо
public class D_File extends DemoBase {

	public void test1() throws Exception {
		/*
		File(File parent, String child)
		File(String pathname)
		File(URI uri)
		 */
		

		File d1 = Paths.get("/home/tetsuma/myfiles/").toFile();		
		
		File f1 = new File("/home/tetsuma/myfiles/theft.txt");
		File f2 = new File(d1, "tmp.txt");

		
		logEval(
				f1,
				f1.exists(),
				f1.isDirectory(),
				f1.isFile(),
				f1.length(),
				f1.isHidden(),
				f1.lastModified(),
				f1.canRead(),
				f1.canWrite()
				);

		/*
		r.set(
				f1.exists(),
				f1.isDirectory(),
				f1.isFile(),
				f1.length(),
				f1.isHidden(),
				f1.lastModified(),
				f1.canRead(),
				f1.canWrite()
				);
		*/

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
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);
		addTest2Button(null);
		addTest3Button(null);
		addTest4Button(null);
	}

	public static void main(String[] args) {
		DemoBase.run(D_File.class);
	}

}
