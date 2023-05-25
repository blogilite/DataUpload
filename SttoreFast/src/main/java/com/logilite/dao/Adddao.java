package com.logilite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.logilite.Model.Student;

public class Adddao {
	private static final String SQL = "Insert into students values(?,?,?,?,?);";
	static Connection con;
	private String url = "jdbc:postgresql://localhost:5432/bom";
	private String userId = "bhautik";
	private String pass = "Bkp1@4521";
	private static String path = "COPY students (student_id,name,address,email,number) FROM '/home/bhautik/Downloads/students.csv' DELIMITER ',' CSV";

	public Adddao() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, userId, pass);
	}

	public static int[] add(ArrayList<Student> stu) throws SQLException {	

		PreparedStatement pstmt = con.prepareStatement(SQL);
		for (Student st : stu) {
			pstmt.setInt(1, st.getsId());
			pstmt.setString(2, st.getsName());
			pstmt.setString(3, st.getsAddress());
			pstmt.setString(4, st.getsEmail());
			pstmt.setLong(5, st.getsNumber());
			pstmt.addBatch();
		}
		int[] dt = pstmt.executeBatch();
		return dt;
	}

	public ArrayList<List<String>> showData() {
		ArrayList<List<String>> ar = new ArrayList<List<String>>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM public.students\n" + "ORDER BY student_id ASC LIMIT 100;");

			while (rs.next()) {
				List<String> l = new ArrayList<>();
				l.add(rs.getString(1));
				l.add(rs.getString(2));
				l.add(rs.getString(3));
				l.add(rs.getString(4));
				l.add(rs.getString(5));
				ar.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ar;
	}

	public static boolean takeData() {
		try {
			Statement statement = con.createStatement();
			statement.execute(path);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean delete() {
		try {
			Statement stmt = con.createStatement();
			boolean b = stmt.execute("truncate students;");
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
