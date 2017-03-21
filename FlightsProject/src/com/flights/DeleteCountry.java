package com.flights;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCountry")
public class DeleteCountry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("country_id") != null) {
			int country_id = Integer.valueOf(request.getParameter("country_id"));
			CountryAccess.delete(country_id);
			response.sendRedirect("Countries");
		} else {
			getServletConfig().getServletContext().
				getRequestDispatcher("/countries.jsp").
				forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
