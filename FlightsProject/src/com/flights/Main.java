package com.flights;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello, World!");
		
		List<Flight> flights = FlightAccess.readAll();
		for (Flight flight : flights) {
			System.out.println(flight);
		}
		
		// NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
        Database.getEntityManagerFactory().close();
	}
}