package javameetup.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import javameetup.entity.City;

@Component
public class CityRepository {
	
    List<City> cities = new ArrayList<City>();
    
    //Metoda parsira json objekat i setovanje objekata tipa City
	public List<City> getAll(Object obj) {
		
		JSONObject jo = (JSONObject) obj; 
       
        JSONArray ja = (JSONArray) jo.get("results"); 
		//Iteriranje kroz JSONArray i preuzimanje parametara potrebnih za kreiranje objekata tipa City
        for (int i = 0; i < ja.size(); i++) {
        	
        	City city = new City();
        	JSONObject jso =(JSONObject) ja.get(i);
        	city.setId(i+1);
        	city.setName(jso.get("city").toString());
        	city.setLat(jso.get("lat").toString());  // Preuzimanje ovih parametara potrebno je 
        	city.setLon(jso.get("lon").toString());  // za pronalazenje eventova.
        	cities.add(city);
		 
		}
        
        return cities;
	}
	//Pronalazenje gradova o id-u.
	public City findById(Long id) {
		for (City city: cities) {
			if (city.getId() == id ) {
				return city;
			}
		}
		return null;
	}
	
	
}
