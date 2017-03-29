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

@Path("/airlines")
public static class AirlineResource {
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public List<Airline> getAllAirlines() {
    return AirlineAccess.readAll();
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  public void createAirline(String name, Airport base_airport) {
    AirlineAccess.create(name, base_airport);
  }
  
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public void updateAirline(int id, String name, Airport base_airport) {
    AirlineAccess.update(id, name, base_airport);
  }
  
  @DELETE
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_XML)
  public void deleteAirline(@PathParam("id") int id) {
    AirlineAccess.delete(id);
  }
}
