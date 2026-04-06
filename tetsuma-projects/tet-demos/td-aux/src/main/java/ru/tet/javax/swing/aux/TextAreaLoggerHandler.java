package ru.tet.javax.swing.aux;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.JTextArea;

/**
 * StreamHandler, выводящий сообщения в JTextArea
 * @author tetsuma
 *
 */
public class TextAreaLoggerHandler extends StreamHandler {

	public TextAreaLoggerHandler(JTextArea textArea) {
		super(new TextAreaOutputStream(textArea), new SimpleFormatter());
	}

	@Override
	public String getEncoding() {
		return StandardCharsets.UTF_8.name();
	}

	@Override
	public synchronized void publish(LogRecord logRecord) {
		super.publish(logRecord);
		flush();
	}

	@Override
	public synchronized void close() {
		flush();
	}
	
	
	static class TextAreaOutputStream extends OutputStream {
		  private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		  private final JTextArea textArea;

		  protected TextAreaOutputStream(JTextArea textArea) {
		    super();
		    this.textArea = textArea;
		  }

		   @Override public void flush() {
		     textArea.append(buffer.toString(StandardCharsets.UTF_8));
		     buffer.reset();
		   }


		  @Override public void write(int b) {
		    buffer.write(b);
		  }

		  @Override public void write(byte[] b, int off, int len) {
		    buffer.write(b, off, len);
		  }
		}	
	
}
