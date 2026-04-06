package ru.tet.java.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class StatementDemo {

	public static final SimpleDateFormat SIMPLE_DATETIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public static void main(String[] args) throws Exception {

//		ddlDemo();
//		insertDemo();

//		selectExecuteDemo();

		batchDemo();

//		selectDemo();
	}

	public static void ddlDemo() throws Exception {

		String sql = "CREATE table gar_test (id int NOT NULL,name varchar(50),startdate date,isactive bool);";

		try (Connection cnn = createCnn(); Statement statement = cnn.createStatement();) {
			statement.executeUpdate(sql);
		}

	}

	public static void insertDemo() throws Exception {

		String sql = "INSERT INTO gar_test(id, name, startdate, isactive) VALUES(1, 'my name', '2016-03-02 12:05:00', false)";

		try (Connection cnn = createCnn(); Statement statement = cnn.createStatement();) {
			System.out.println(sql);
			int count = statement.executeUpdate(sql);
			System.out.format("inserted %d rows%n", count);
		}

	}

	public static void batchDemo() throws Exception {

		String sql1 = "INSERT INTO gar_test(id, name, startdate, isactive) VALUES(2, 'white wall', '2016-03-02 12:05:00', false)";
		String sql2 = "INSERT INTO gar_test(id, name, startdate, isactive) VALUES(3, 'red door', '2016-03-02 9:20:00', true)";

		try (Connection cnn = createCnn(); Statement statement = cnn.createStatement();) {
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			statement.executeBatch();
			cnn.commit();
		}

	}

	public static void selectExecuteDemo() throws Exception {

		String sql = "select * from gar_test";

		try (Connection cnn = createCnn(); Statement statement = cnn.createStatement();) {

			System.out.println(sql);
			boolean r = statement.execute(sql);
			if (r) {
				ResultSet rs = statement.getResultSet();

				while (rs.next()) {
					int f1 = rs.getInt("id");
					String f2 = rs.getString("name");
					Timestamp f3 = rs.getTimestamp("startdate");
					System.out.format("(%d,'%s',%s)%n", f1, f2, SIMPLE_DATETIME_FORMAT.format(f3));

				}
				rs.close();
			}

		}
	}

	public static void selectDemo() throws Exception {

		String selectSql = "select id, version, deltadate , start_date from gar_delta_load_history order by id";

		try (Connection cnn = createCnn();
				Statement statement = cnn.createStatement();
				ResultSet rs = statement.executeQuery(selectSql);) {
			while (rs.next()) {
				int f1 = rs.getInt(1);
				String f2 = rs.getString("version");
				Date f3 = rs.getDate(3);
				Timestamp f4 = rs.getTimestamp(4);
				System.out.format("(%d,'%s',%s,%s)%n", f1, f2, SIMPLE_DATE_FORMAT.format(f3),
						SIMPLE_DATETIME_FORMAT.format(f4));

			}

		}
	}

	static Connection createCnn() throws Exception {
		Class.forName("org.postgresql.Driver");

		Connection cnn = DriverManager.getConnection("jdbc:postgresql://10.17.0.20:5432/tcm_test", "tcm_test",
				"tcm_test");
		return cnn;
	}

}
