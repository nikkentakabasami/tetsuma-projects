package ru.tet.javax.swing.layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.SwingUtilities;

import ru.tet.beans.SuIdNameModel;
import ru.tet.javax.swing.aux.JFrameForTests;

public class FlowLayoutDemo2 extends JFrameForTests {

//	int currentHGap = 0;
//	int currentVGap = 0;
	
	FlowLayout layout;


	@Override
	protected void doInit() {

		initWithControlPanelAbove();


		layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		workPanel.setLayout(layout);

		Dimension labelSize = new Dimension(200, 50);
		
		for (int i = 0; i < 10; i++) {
			workPanel.add(createDemoLabel("label " + i, Color.GREEN,labelSize));
		}


		//add alignment combo box
		SuIdNameModel[] data = new SuIdNameModel[] {
			new SuIdNameModel(FlowLayout.LEFT, "LEFT"),
			new SuIdNameModel(FlowLayout.CENTER, "CENTER"),
			new SuIdNameModel(FlowLayout.RIGHT, "RIGHT")
		};
		controlPanel.addComboBox(data, e->{
			SuIdNameModel item = (SuIdNameModel)controlPanel.comboBox1.getSelectedItem();
			
			layout.setAlignment(item.getId());
			workPanel.setLayout(layout);
			showLayoutInfo();
		});
		

		//add HGap change button
		controlPanel.addButton("switch HGap", e -> {
			
			int currentHGap = layout.getHgap();
			
			currentHGap = (currentHGap+5)%100;
			layout.setHgap(currentHGap);
			workPanel.setLayout(layout);
			showLayoutInfo();
		});
		
		//add HGap change button
		controlPanel.addButton("switch VGap", e -> {
			int currentVGap = layout.getVgap();
			
			currentVGap = (currentVGap+5)%100;
			layout.setVgap(currentVGap);
			workPanel.setLayout(layout);
			showLayoutInfo();
		});
		controlPanel.addDebugLabel();
		
		showLayoutInfo();
		
	}

	private void showLayoutInfo() {

		SuIdNameModel align = (SuIdNameModel)controlPanel.comboBox1.getSelectedItem();
		
		String s = String.format("align: %s, HGap: %d, VGap: %d",align.getName(), layout.getHgap(), layout.getVgap());
		controlPanel.label1.setText(s);
		
		
	}
	

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			FlowLayoutDemo2 demo = new FlowLayoutDemo2();
			demo.doInit();
		});		
		
	}
	
}
