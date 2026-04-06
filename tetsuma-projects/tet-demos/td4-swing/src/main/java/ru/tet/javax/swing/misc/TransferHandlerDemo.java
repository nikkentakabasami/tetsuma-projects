package ru.tet.javax.swing.misc;

import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import ru.tet.javax.swing.aux.JFrameForTests;

public class TransferHandlerDemo extends JFrameForTests {


	@Override
	protected void doInit() throws Exception {

		initWithControlPanelAbove();

		controlPanel.addTextArea();		
		
		JLabel label = new JLabel("Перетащи файл сюда",demoImages.missingIcon,SwingConstants.LEFT);
		workPanel.add(label);
		
		
		label.setTransferHandler(new TransferHandler() {
		    @Override
		    public boolean canImport(TransferSupport support) {
		        // Разрешаем импорт только файлов
		        return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
		    }

		    @Override
		    public boolean importData(TransferSupport support) {
		        if (!canImport(support)) {
		            return false;
		        }
		        try {
		        	List<File> files = (List<File>)
		                support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            for (File file : files) {
		            	controlPanel.textArea.append("Импортирован файл: " + file.getAbsolutePath());
		            }
		            return true;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return false;
		    }
		});		
		
		
		
		
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TransferHandlerDemo demo = new TransferHandlerDemo();
			demo.init();
		});		
	}

}
