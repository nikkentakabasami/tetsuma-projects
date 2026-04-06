package ru.tet.javax.swing.misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.Icon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ru.tet.javax.swing.aux.JFrameForGraphicsTests;

/**
 * Просмотр иконок, которые имеются в системе по умолчанию.
 * 
 * @author tetsuma
 *
 */
public class LookAndFeelsDemoDemo2_defaultIcons extends JFrameForGraphicsTests {

	Icon missingIcon;
	
	List<String> iconkeys;
	
	@Override
	protected void doInit() throws Exception {
		initWithCanvasComponent();

		Set<Object> defaultKeys = UIManager.getLookAndFeelDefaults().keySet();
		
		//.filter(s->s.toLowerCase().contains("icon"))
		iconkeys = defaultKeys.stream().map(o->o.toString()).collect(Collectors.toList());

		
		controlPanel.addTextArea();
		
		for(String iconKey:iconkeys) {
            System.out.println(iconKey);
		}
		
	    
	}


	@Override
	public void paintInCanvas(Graphics2D g2) throws Exception {

		controlPanel.textArea.setText("");
		
		Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);
		
		int iconIndex = 0;
		for (int x = 0; x < 1550; x+=250) {
			
			for (int y = 0; y < 650; y+=50) {
				
				
				Icon icon = null;
				String iconKey = null;
				do {
					if (iconIndex>=iconkeys.size()) {
						return;
					}

					iconKey = iconkeys.get(iconIndex);
					icon = UIManager.getIcon(iconKey);
					iconIndex++;
				} while (icon==null);
				
				
//				icon.paintIcon(canvasComp, g2, x, y);
				
				try {
					icon.paintIcon(null, g2, x, y);
					g2.setColor(Color.BLACK);
					controlPanel.textArea.append(String.format("icon = UIManager.getIcon(\"%s\");\n",iconKey));
					
										
					
					
				} catch (Exception e) {
					g2.setColor(Color.RED);
				} finally {
					g2.drawString(iconKey, x+50, y+icon.getIconHeight());
				}
				
				
				
			}
			
			
			
		}
		
		
		
		
//	    missingIcon = UIManager.getIcon("html.missingImage");
//		missingIcon.paintIcon(canvasComp, g2, 0, 0);
        
		
	}
	
	
	
	

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> {
			LookAndFeelsDemoDemo2_defaultIcons frame = new LookAndFeelsDemoDemo2_defaultIcons();
			frame.init();
		});

	}

}
