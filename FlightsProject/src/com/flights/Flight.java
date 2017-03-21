package com.flights;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@ManyToOne
	@JoinColumn(name = "from_airport_id")
	public Airport from_airport;
	
	@ManyToOne
	@JoinColumn(name = "to_airport_id")
	public Airport to_airport;
	
	@ManyToOne
	@JoinColumn(name = "airline_id")
	public Airline airline;
	
	@Column(name = "days", nullable = false)
	public String days;
	
	@Column(name = "from_time", nullable = false)
	public String from_time;
	
	@Column(name = "to_time", nullable = false)
	public String to_time;
	
	@ManyToOne
	@JoinColumn(name = "plane_id")
	public Plane plane;
	
	@Override
	public String toString() {
		return String.format("%s (%s) - %s (%s), %s:%s - %s:%s, %s, %s, %s %s (%d seats)",
				this.from_airport.city.name,
				this.from_airport.code,
				this.to_airport.city.name,
				this.to_airport.code,
				this.from_time.substring(0, 2),
				this.from_time.substring(2, 4),
				this.to_time.substring(0, 2),
				this.to_time.substring(2, 4),
				this.days,
				this.airline.name,
				this.plane.manufacturer,
				this.plane.model,
				this.plane.seats);
	}
}

class FlightAccess {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
			Database.getEntityManagerFactory();
	
	public static void create(Airport from_airport, Airport to_airport, Airline airline, String days, String from_time, String to_time, Plane plane) {
		// Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            
            // Create a new Flight object
            Flight flight = new Flight();
            flight.from_airport = from_airport;
            flight.to_airport = to_airport;
            flight.airline = airline;
            flight.days = days;
            flight.from_time = from_time;
            flight.to_time = to_time;
            flight.plane = plane;

            // Save the flight object
            manager.persist(flight);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
	}
	
	public static List<Flight> readAll() {

        List<Flight> flights = null;

        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get a List of Flights
            flights = manager.createQuery("SELECT f FROM Flight f",
            		Flight.class).getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return flights;
    }
	
	public static void delete(int id) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Flight object
            Flight flight = manager.find(Flight.class, id);

            // Delete the flight
            manager.remove(flight);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }
	
	public static void update(int id, Airport from_airport, Airport to_airport, Airline airline, String days, String from_time, String to_time, Plane plane) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Flight object
            Flight flight = manager.find(Flight.class, id);

            // Change the values
            flight.from_airport = from_airport;
            flight.to_airport = to_airport;
            flight.airline = airline;
            flight.days = days;
            flight.from_time = from_time;
            flight.to_time = to_time;
            flight.plane = plane;
            
            // Update the flight
            manager.persist(flight);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }
}