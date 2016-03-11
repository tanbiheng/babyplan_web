package com.gem.babyplan.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
	// 通过方法获得Connection
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		// 配置文件：XML文件，properties：key=values
		// 第一种注册驱动方法：
		Properties p = new Properties();
		p.load(DBConnection.class.getResourceAsStream("db.properties"));
		String driver = p.getProperty("driver");
		Class.forName(driver);
		// 第二种注册驱动方法：
		// Driver driver = new Driver();
		// DriverManager.registerDriver(driver);
		// 第三种注册驱动方法：
		// System.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
		String url = p.getProperty("url");
		String user = p.getProperty("user");
		String password = p.getProperty("password");
		return DriverManager.getConnection(url, user, password);
	}

	public static void release(Connection conn, PreparedStatement prep, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (prep != null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void release(Connection conn, PreparedStatement prep) {
		if (prep != null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		System.out.println(getConnection());
	}
}
