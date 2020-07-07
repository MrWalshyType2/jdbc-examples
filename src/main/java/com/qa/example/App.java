package com.qa.example;

public class App {
	public static void main(String[] args) {
		JDBC db = new JDBC("root", "root");

		db.connect();
	}
}
