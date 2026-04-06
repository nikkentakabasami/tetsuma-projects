package ru.tet.javax.swing.components;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;

import ru.tet.javax.swing.aux.DemoDataSamples;
import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.PropertiesTableModel;

public class JTableDemo4_PropertiesTableModel extends JFrameForTests {

	JTable table;
	PropertiesTableModel model;

	@Override
	protected void doInit() {
		
		initWithControlPanelAbove(null);

		controlPanel.addButton("saveToFile", e->{

			try {
				File f = new File("target/test.prop");
				model.saveData(f);
				//показываем содержимое файла
				String content = FileUtils.readFileToString(f,StandardCharsets.UTF_8);
				controlPanel.textArea.setText(content);
			} catch (IOException e1) {
			}
		});
		
		controlPanel.addButton("load from file", e->{

			try {
				File f = new File("target/test.prop");
				model.loadData(f);
			} catch (IOException e1) {
			}
		});		
		
		
		controlPanel.addTextArea();
		
		controlPanel.addDebugLabel();

		model = new PropertiesTableModel();
		model.loadData(DemoDataSamples.tableData3);
		
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		workPanel.setLayout(new BorderLayout());
		workPanel.add(scrollPane,SwingConstants.CENTER);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JTableDemo4_PropertiesTableModel frame = new JTableDemo4_PropertiesTableModel();
			frame.init();
		});
	}

}
