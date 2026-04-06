package ru.tet.javax.swing.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

import ru.tet.beans.Comment;
import ru.tet.javax.swing.aux.DynamicTableModel;
import ru.tet.javax.swing.aux.JFrameForTests;
import ru.tet.javax.swing.aux.StripedTable;
import ru.tet.javax.swing.aux.TableColumnDesc;
import ru.tet.javax.swing.aux.TableEditPopupMenu;

public class JTableDemo3_CustomTableModel extends JFrameForTests {

	JTable table;
	DynamicTableModel<Comment> model;

	TableEditPopupMenu popupMenu;
	
	@Override
	protected void doInit() {
		initWithControlPanelAbove();
		workPanel.setLayout(new BorderLayout());

		controlPanel.addDebugLabel();

		createTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(450, 200));

		

		workPanel.add(scrollPane, SwingConstants.CENTER);

	}

	private void createTable() {

		TableColumnDesc[] columns = { 
				new TableColumnDesc("id", Integer.class, false,"Id"),
				new TableColumnDesc("name", String.class, false,"Имя"), 
				new TableColumnDesc("comment", String.class, false,"Коммент") };
		
		
		model = new DynamicTableModel<>(columns);
		
		List<Comment> rows = new ArrayList<>();
		rows.add(new Comment(1, "Name 1", "comment..."));
		rows.add(new Comment(2, "Name 2", "Test"));
		rows.add(new Comment(3,"Name d", "ee" ));
		rows.add(new Comment(4, "Name c", "Test cc"));
		rows.add(new Comment(5,"Name b", "Test bb" ));
		rows.add(new Comment(6, "Name a", "ff"));
		rows.add(new Comment(7, "Name 0", "Test aa"));
		model.setRows(rows);
		
		table = new StripedTable(model);

		TableColumn col = table.getColumnModel().getColumn(0);
		col.setMinWidth(60);
		col.setMaxWidth(60);
		col.setResizable(false);


		table.setAutoCreateRowSorter(true);
		table.setFillsViewportHeight(true);
		
		
		
		
		popupMenu = new TableEditPopupMenu(table, ()->{
			return new Comment(table.getRowCount()+1, "-", "");
		});
		
		table.setComponentPopupMenu(popupMenu);
		

	}




	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			JTableDemo3_CustomTableModel frame = new JTableDemo3_CustomTableModel();
			frame.init();
		});

	}

}
