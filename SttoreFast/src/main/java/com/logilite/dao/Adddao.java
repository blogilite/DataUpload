package com.logilite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.logilite.Model.Student;

public class Adddao {
	private static final String SQL = "Insert into students values(?,?,?,?,?);";
	static Connection con;
	private String url = "jdbc:postgresql://localhost:5432/bom";
	private String userId = "bhautik";
	private String pass = "Bkp1@4521";

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

	public ArrayList<Student> showData() {
		ArrayList<Student> ar = new ArrayList<Student>();
		return ar;
	}

}
