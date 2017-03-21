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
@Table(name = "plane")
public class Plane implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Column(name = "manufacturer", nullable = false)
	public String manufacturer;
	
	@Column(name = "model", nullable = false)
	public String model;
	
	@Column(name = "seats", nullable = false)
	public int seats;
	
	@ManyToOne
	@JoinColumn(name = "airline_id")
	public Airline airline;
	
	@Column(name = "amount", nullable = false)
	public int amount;
	
	@Override
	public String toString() {
		return String.format("%s %s (%d seats), %s (%s, %s, %s), %d items", this.manufacturer, this.model, this.seats, this.airline.name, this.airline.base_airport.code, this.airline.base_airport.city.name, this.airline.base_airport.city.country.name, this.amount);
	}
}

class PlaneAccess {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
			Database.getEntityManagerFactory();
	
	public static void create(String manufacturer, String model, int seats, Airline airline, int amount) {
		// Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            
            // Create a new Plane object
            Plane plane = new Plane();
            plane.manufacturer = manufacturer;
            plane.model = model;
            plane.seats = seats;
            plane.airline = airline;
            plane.amount = amount;

            // Save the plane object
            manager.persist(plane);

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
	
	public static List<Plane> readAll() {

        List<Plane> planes = null;

        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get a List of Planes
            planes = manager.createQuery("SELECT p FROM Plane p",
            		Plane.class).getResultList();

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
        return planes;
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

            // Get the Plane object
            Plane plane = manager.find(Plane.class, id);

            // Delete the plane
            manager.remove(plane);

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
	
	public static void update(int id, String manufacturer, String model, int seats, Airline airline, int amount) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Plane object
            Plane plane = manager.find(Plane.class, id);

            // Change the values
            plane.manufacturer = manufacturer;
            plane.model = model;
            plane.seats = seats;
            plane.airline = airline;
            plane.amount = amount;

            // Update the plane
            manager.persist(plane);

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