package com.flights;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Countries")
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("country_id") != null) {
			int country_id = Integer.valueOf(request.getParameter("country_id"));
			List<Country> countries = CountryAccess.readAll();
			Country choosed_country = null;
			for (Country country : countries) {
				if (country.id == country_id) {
					choosed_country = country;
					break;
				}
			}
			
			request.setAttribute("country", choosed_country);
			getServletConfig().getServletContext().
				getRequestDispatcher("/country.jsp").
				forward(request, response);
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
