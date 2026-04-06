package ru.tet.javax.swing.graphics;

import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ru.tet.javax.swing.aux.GBC;
import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.LoadingIcon;

public class AffineTransformDemo4_loadingIcon extends JFrameForTests {

	JLabel label;
	
	LoadingIcon icon;
	
	Timer animateTimer = new Timer(100, null);
	
	
	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		controlPanel.addComboBox(new String[] { "paintMode0 - AlphaComposite", "paintMode1 - darker сolor", "paintMode2 - createTransformedShape" },
				e-> {
					int mode = controlPanel.comboBox1.getSelectedIndex();
					icon.setPaintMode(mode);
					label.repaint();
					});
		
		
	    workPanel.setLayout(new GridBagLayout()); 
		
	    icon = new LoadingIcon();
	    
		label = new JLabel("My icon", icon, SwingConstants.LEFT);
		
		workPanel.add(label, new GBC(0, 0));
		workPanel.add(Box.createVerticalGlue(), new GBC(0, 1,1,1,1,1));
		

		
		animateTimer = new Timer(150, e->{
			icon.next();
			
			long startTime = System.nanoTime();
			label.repaint();
			long elapsedNanos = System.nanoTime() - startTime;			
			controlPanel.textArea.append(elapsedNanos+"\n");
			
			
		});
		
		controlPanel.addButton("animate", e->{
			if (animateTimer.isRunning()) {
				animateTimer.stop();
			} else {
				animateTimer.start();
			}
			
		});
		
		controlPanel.addTextArea();
		
		
//	    workPanel.setLayout(new BorderLayout()); 
//	    workPanel.setLayout(new GridLayout(1,2)); 
//	    workPanel.setLayout(new BoxLayout(workPanel, BoxLayout.Y_AXIS));
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			AffineTransformDemo4_loadingIcon frame = new AffineTransformDemo4_loadingIcon();
			frame.init();
		});

	}

}
