package ru.tet.java.beans;

import java.awt.Font;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

import ru.tet.aux.swing.DemoBase;

public class PropertyEditorDemo extends DemoBase {

	//java.beans.PropertyEditor- Интерфейс для конвертации строки в объект(и обратно)
	//Используется для задания значений в бин в строковом виде.
	//Основные методы реализованы через PropertyEditorSupport, который является родителем всех проперти эдиторов.
	@Override
	public void test1() throws Exception {

		//PropertyEditorManager- Позволяет искать и регистрировать РЕ, которые могут понадобиться.
		PropertyEditor editor = PropertyEditorManager.findEditor(Font.class);
		//font to text conversion
		editor.setValue(new Font(Font.DIALOG, Font.BOLD, 12));
		String str = editor.getAsText();
		log2("Font to String:",str);

		//text to font conversion
		PropertyEditor editor2 = PropertyEditorManager.findEditor(Font.class);
		editor2.setAsText("SansSerif ITALIC 14");
		Object value = editor2.getValue();
		log2("String to Font:",value);

	}


	@Override
	protected void doInitControlPanel() throws Exception {
		addTest1Button(null);

	}

	public static void main(String[] args) {
		DemoBase.run(PropertyEditorDemo.class);
	}

}
