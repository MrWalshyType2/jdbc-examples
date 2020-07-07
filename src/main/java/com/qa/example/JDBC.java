package com.qa.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	private String jdbcConnectionURL;
	private String username = "root";
	private String password = "root";

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	public JDBC(String username, String password) {
		jdbcConnectionURL = "jdbc:mysql://localhost:3306/gamedb?serverTimezone=UTC";
		this.username = username;
		this.password = password;
	}

	public JDBC(String jdbcConnectionURL, String username, String password) {
		this.jdbcConnectionURL = jdbcConnectionURL;
		this.username = username;
		this.password = password;
	}

	public void connect() {
		Connection conn = null;
		Statement stmt = null;

		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open connection
			conn = DriverManager.getConnection(jdbcConnectionURL, username, password);

			// readCustomer(5);
			// create();
			// readAllCustomers();
			// updateCustomer(5, "newPass");
			// deleteCustomer(5);
			readAllCustomers();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void create() {
		try {
			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();

			statement.executeUpdate("INSERT INTO customer(name, address, email, password)"
					+ " VALUES('Bob', 'MyStreet', 'someemail22@mail.com', 'bluepass')");

			conn.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readCustomer(int id) {
		try {
			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE customer_id = " + id);

			rs.next();
			String name = rs.getString("name");
			String email = rs.getString("email");
			String password = rs.getString("password");

			// Display values
			System.out.println("Fetching information...");
			System.out.println("ID = " + id + "\nName = " + name + "\nEmail = " + email + "\nPassword = " + password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readAllCustomers() {
		try {
			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM customer");

			// Extract data from result set
			while (rs.next()) {
				int id = rs.getInt("customer_id");
				String name = rs.getString("name");
				String email = rs.getString("email");

				// Display values
				System.out.println("Fetching information...");
				System.out.println("ID = " + id + "\nName = " + name + "\nEmail = " + email);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCustomer(int id, String newPw) {
		try {
			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();

			statement.executeUpdate("UPDATE customer SET password = '" + newPw + "' " + "WHERE customer_id = " + id);

			readCustomer(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCustomer(int id) {
		try {
			Connection conn = DriverManager.getConnection(jdbcConnectionURL, username, password);
			Statement statement = conn.createStatement();

			statement.executeUpdate("DELETE FROM customer WHERE customer_id = " + id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
