package ru.tet.javax.swing.aux;

import java.io.IOException;
import java.io.Writer;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


/**
 * Группа хаков, предназначенная для того, чтобы компонент распознавал относительные пути к картинкам
 * (они будут искаться относительно рабочей папки)
 * 
 * @author tetsuma
 *
 */
public class MyHTMLEditorKit extends HTMLEditorKit {

	
	
	
	@Override
	public void write(Writer out, Document doc, int pos, int len)
			throws IOException, BadLocationException {

		MyHTMLWriter w = new MyHTMLWriter(out, (HTMLDocument)doc, pos, len);
	    w.write();
		
	}

	
	
}
