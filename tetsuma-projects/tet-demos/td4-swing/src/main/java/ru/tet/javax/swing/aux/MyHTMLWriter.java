package ru.tet.javax.swing.aux;

import java.io.IOException;
import java.io.Writer;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLWriter;

/**
 * Хак, для того чтобы русские символы выводились не в кодированном виде
 * @author Mayuri
 *
 */
public class MyHTMLWriter extends HTMLWriter {

	
    public MyHTMLWriter(Writer w, HTMLDocument doc, int pos, int len) {
    	super(w, doc, pos, len);
	}
	
	
    
    @Override
    protected void output(char[] chars, int start, int length)
    		throws IOException {

    	if (length==0)
    		return;
    	
    	char[] buf=new char[length];
    	int bufDataLen=0;
    	
    	int lastIndex=start+length;
    	for (int i = start; i < lastIndex; i++) {
		
    		char c=chars[i];
    		
    		if ((c>='А' && c<='я') || (c=='ё') || (c=='Ё')  ){  //Если символ русский
    			
    			if (bufDataLen>0){ //выводим содержимое буфера
    				super.output(buf, 0, bufDataLen);
    				bufDataLen=0;
    			}
    			
    			//и записываем русский символ отдельно
    			getWriter().write(chars, i, 1);
    			setCurrentLineLength(getCurrentLineLength() + length);
    		}
    		else {
    			buf[bufDataLen]=c;
    			bufDataLen++;
    		}
    		
		}
    	
		if (bufDataLen>0){
			super.output(buf, 0, bufDataLen);
		}    
    
    
    }

    
}