package com.logilite.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logilite.Model.Student;
import com.logilite.dao.Adddao;

@WebServlet("/FirstPage")
public class FirstPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String numAlpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()";
	private static final String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String num = "0123456789";
	public Adddao ad;

	public FirstPage() throws ClassNotFoundException, SQLException {
		ad = new Adddao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher reqd = request.getRequestDispatcher("index.jsp");
		reqd.include(request, response);
	}

	Instant start;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("add");

		if (name.equals("Generate")) {
			start = Instant.now();
			try {
				ArrayList<Student> dt = new ArrayList<Student>();
				for (int i = 1; i <= 10_00_000; i++) {
					Student st = new Student();
					st.setsId(i);
					st.setsName(generateRandomString(8));
					st.setsAddress(generateRandomString(20));
					st.setsEmail(generateRandomMail(8) + "@gmail.com");
					st.setsNumber(generateRandomNumber(10));
					// System.out.println(st.getsId() + " " + st.getsName() + " " + st.getsAddress()
					// + " " + st.getsEmail() + " " + st.getsNumber());
					dt.add(st);
				}

				int data[] = Adddao.add(dt);
				for (int i = 0; i < data.length; i++) {
					if (data[i] != 1) {
						request.setAttribute("data", "Your data is not Succefull Add");
					}
				}

				request.setAttribute("data", "Your data is Succefull Add");
				Instant end = Instant.now();
				Duration timeElapsed = Duration.between(start, end);
				request.setAttribute("time", timeElapsed);
				RequestDispatcher reqd = request.getRequestDispatcher("/SecondPage");
				reqd.include(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println(timeElapsed);
		// doGet(request, response);
	}

	private String generateRandomMail(int length) {
		Instant start = Instant.now();
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int j = 0; j < length; j++) {
			int randomIndex = random.nextInt(numAlpha.length());
			char randomChar = numAlpha.charAt(randomIndex);
			sb.append(randomChar);
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		// System.out.println("f3" + timeElapsed);
		return sb.toString();
	}

	private static String generateRandomString(int length) {
		Instant start = Instant.now();
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int j = 0; j < length; j++) {
			int randomIndex = random.nextInt(alpha.length());
			char randomChar = alpha.charAt(randomIndex);
			sb.append(randomChar);
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		// System.out.println("f1" + timeElapsed);
		return sb.toString();
	}

	private long generateRandomNumber(int no) {
		Instant start = Instant.now();
		Random random = new Random();
		long sb = 9;

		for (int i = 1; i <= no - 1; i++) {
			int randomIndex = random.nextInt(num.length());
			sb = randomIndex + sb * 10;
		}

		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		// System.out.println("f2" + timeElapsed);
		return sb;
	}

}
