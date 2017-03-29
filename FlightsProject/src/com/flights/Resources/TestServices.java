package com.flights.Resources;

import java.util.List;

public static class TestServices {
  public static void main(String[] args) {
    ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);
	WebResource service = client.resource(getBaseURI());
	
    // Get all countries
    GenericType<List<Country>> genericType = new GenericType<List<Country>>() {};
	List<Country> countries = service.path("rest").path("countries")
      .accept(MediaType.APPLICATION_XML).get(genericType);

    for (Country country : countries) {
      System.out.println(country);
    }
    
    // Create country
   	String countryName = "Russian Federation";
    service.path("rest").path("message").post(countryName);
    
    // Update country
    Country country = countries[0];
    country.name = "Poland";
    service.path("rest").path("message").put(country.id, country.name);
    
    // Delete country
    service.path("rest").path("message").delete(1);
  }
  private static URI getBaseURI() {
	return UriBuilder.fromUri("http://localhost:8080/FlightsProject").build();
  }
}
