package com.flights.Resources;

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

@Path("/airports")
public static class AirportResource {
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public List<Airport> getAllAirports() {
    return AirportAccess.readAll();
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  public void createAirport(String code, String name, City city) {
    AirportAccess.create(code, name, city);
  }
  
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public void updateAirport(int id, String code, String name, City city) {
    AirportAccess.update(id, code, name, city);
  }
  
  @DELETE
  @Path("/{id}")
  public void deleteAirport(@PathParam("id") int id) {
    AirportAccess.delete(id);
  }
}
