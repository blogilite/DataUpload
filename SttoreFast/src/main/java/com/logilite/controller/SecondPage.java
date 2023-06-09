package com.logilite.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logilite.dao.Adddao;

@WebServlet("/SecondPage")
public class SecondPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Adddao ad;

	public SecondPage() throws ClassNotFoundException, SQLException {
		ad = new Adddao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher reqd = request.getRequestDispatcher("show_data.jsp");
		ArrayList<List<String>> l = ad.showData();
		for (List<String> st : l) {
			for (String s : st) {
				System.out.print(s);
			}
			System.out.println();
		}
		request.setAttribute("listData", l);
		reqd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
