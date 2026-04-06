package ru.tet.javax.swing.misc;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ru.tet.javax.swing.aux.JFrameForTests;

public class EventQueueDemo extends JFrameForTests {

	@Override
	protected void doInit() throws Exception {
		initWithControlPanelAbove();

		changeQueue();

		controlPanel.addButton("getCurrentFrame", e->{
			JFrame frame = getActiveFrame();
			JOptionPane.showMessageDialog(null, "frame="+frame.toString()); 
			
			
		});
		
		
		JButton button = new JButton("Создать исключение");

		button.addActionListener(e -> {
			// Искусственно вызываем исключение
			throw new RuntimeException("Ошибка в обработчике");
		});

		workPanel.add(button);

	}

	public static JFrame getActiveFrame() {
		for (Window window : Window.getWindows()) {
			if (window.isVisible() && window.isActive() && window instanceof JFrame) {
				return (JFrame) window;
			}
		}
		return null; // нет активного JFrame
	}

	public static void changeQueue() {

		EventQueue queue = new EventQueue() {
			@Override
			protected void dispatchEvent(AWTEvent event) {
				try {
					super.dispatchEvent(event);
				} catch (Throwable t) {
					JOptionPane.showMessageDialog(null, t.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
					System.err.println("Обнаружено исключение внутри Event Dispatch Thread:");
//	                    t.printStackTrace();
				}
			}
		};

		Toolkit.getDefaultToolkit().getSystemEventQueue().push(queue);

	}

	public static void main(String[] args) throws Exception {


		SwingUtilities.invokeLater(() -> {
			EventQueueDemo frame = new EventQueueDemo();
			frame.init();
		});

	}

}
