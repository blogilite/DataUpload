package com.logilite.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logilite.dao.Adddao;

@WebServlet("/FirstPage")
public class FirstPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private static final String numAlpha =
	// "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()";
	// private static final String alpha =
	// "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	// private static final String num = "0123456789";
	public Adddao ad;
	private Instant start;

	public FirstPage() throws ClassNotFoundException, SQLException {
		ad = new Adddao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher reqd = request.getRequestDispatcher("index.jsp");
		reqd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("add");

		if (name.equals("Generate")) {
			start = Instant.now();

			try {
				if (Adddao.takeData()) {
					request.setAttribute("data", "Your data is Succefull Add");
				} else {
					request.setAttribute("data", "Your data is not Succefull Add");
				}
				Instant end = Instant.now();
				Duration timeElapsed = Duration.between(start, end);
				request.setAttribute("time", timeElapsed);
				RequestDispatcher reqd = request.getRequestDispatcher("/SecondPage");
				reqd.include(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (name.equals("Delete")) {
			try {
				if (Adddao.delete()) {
					request.setAttribute("data", "Your data is Succefull Add");
					RequestDispatcher reqd = request.getRequestDispatcher("index.jsp");
					reqd.include(request, response);
				} else {
					request.setAttribute("data", "Your data is Succefull Add");
					RequestDispatcher reqd = request.getRequestDispatcher("index.jsp");
					reqd.include(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
