package com.flights;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/flights")
public static class FlightResource {
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public List<Plane> getAllFlights() {
    return FlightAccess.readAll();
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  public void createFlight(Airport from_airport, Airport to_airport, Airline airline, String days, String from_time, String to_time, Plane plane) {
    FlightAccess.create(from_airport, to_airport, airline, days, from_time, to_time, plane);
  }
  
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public void updateFlight(int id, Airport from_airport, Airport to_airport, Airline airline, String days, String from_time, String to_time, Plane plane) {
    FlightAccess.update(id, from_airport, to_airport, airline, days, from_time, to_time, plane);
  }
  
  @DELETE
  @Path("{id}")
  public void deleteFlight(@PathParam("id") int id) {
    FlightAccess.delete(id);
  }
}
