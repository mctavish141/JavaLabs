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

@Path("/planes")
public static class PlaneResource {
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public List<Plane> getAllPlanes() {
    return PlaneAccess.readAll();
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  public void createPlane(String manufacturer, String model, int seats, Airline airline, int amount) {
    PlaneAccess.create(manufacturer, model, seats, airline, amount);
  }
  
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public void updatePlane(int id, String manufacturer, String model, int seats, Airline airline, int amount) {
    PlaneAccess.update(id, manufacturer, model, seats, airline, amount);
  }
  
  @DELETE
  @Path("/{id}")
  public void deletePlane(@PathParam("id") int id) {
    PlaneAccess.delete(id);
  }
}
