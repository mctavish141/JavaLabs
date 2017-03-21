package com.flights;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
	// Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Flights");
    
    public static EntityManagerFactory getEntityManagerFactory() {
    	return ENTITY_MANAGER_FACTORY;
    }
}
