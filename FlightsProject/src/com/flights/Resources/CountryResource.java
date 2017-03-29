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

@Path("/countries")
public static class CountryResource {
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public List<Country> getAllCountries() {
    return CountryAccess.readAll();
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  public void createCountry(String name) {
    CountryAccess.create(name);
  }
  
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public void updateCountry(int id, String name) {
    CountryAccess.update(id, name);
  }
  
  @DELETE
  @Path("/{id}")
  public void deleteCountry(@PathParam("id") int id) {
    CountryAccess.delete(id);
  }
}
