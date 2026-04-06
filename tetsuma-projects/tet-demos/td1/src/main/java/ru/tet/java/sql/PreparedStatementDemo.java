package ru.tet.java.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PreparedStatementDemo {

	public static final SimpleDateFormat SIMPLE_DATETIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public static void main(String[] args) throws Exception {

//		ddlDemo();
		insertDemo();

//		selectExecuteDemo();

//		batchDemo();

//		selectDemo();
	}

	public static void ddlDemo() throws Exception {

		String sql = "CREATE table gar_test2 (id int NOT NULL,name varchar(50),startdate date,isactive bool);";

		try (Connection cnn = createCnn(); PreparedStatement statement = cnn.prepareStatement(sql)) {
			statement.executeUpdate();
		}

	}

	public static void insertDemo() throws Exception {

		String sql = "INSERT INTO gar_test(id, name, startdate, isactive) VALUES(?, ?, ?, ?)";

		try (Connection cnn = createCnn(); PreparedStatement statement = cnn.prepareStatement(sql)) {
			
			statement.setInt(1, 11);
			statement.setString(2, "isu");
			statement.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			statement.setBoolean(4, false);
			int count = statement.executeUpdate();
			cnn.commit();
			System.out.format("inserted %d rows%n", count);
		}

	}



	static Connection createCnn() throws Exception {
		Class.forName("org.postgresql.Driver");

		Connection cnn = DriverManager.getConnection("jdbc:postgresql://10.17.0.20:5432/tcm_test", "tcm_test",
				"tcm_test");
		return cnn;
	}

}
