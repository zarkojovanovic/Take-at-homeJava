package javameetup.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import javameetup.entity.City;
import javameetup.entity.Event;

@Component
public class EventRepository {
	
	
	
	//Metoda parsira json objekat i setovanje objekata tipa Event
	public List<Event> getAll(Object obj, City city) {
		List<Event> events = new ArrayList<Event>();
		JSONObject jo = (JSONObject) obj;
		
		JSONArray ja = (JSONArray) jo.get("events");
		
		//Iteriranje kroz JSONArray i preuzimanje parametara potrebnih za kreiranje objekata tipa Event
		for (int i = 0; i < ja.size(); i++) {
			
			Event event = new Event();
			JSONObject jso = (JSONObject) ja.get(i);
			event.setId(i+1);                           			// Id se setuje dodavanjem broja jedan na brojac
			event.setName(jso.get("name").toString());
			try {
			String description = jso.get("description").toString();	
		    description= description.replaceAll("\\<.*?\\>", "");    // Brisanje html tagova iz Stringa	
			event.setDescription(description);
			
			}catch (NullPointerException e) {
				event.setDescription("No description");       		// Ukoliko ne postoji description za trenutni objekat
															  		// da program ne bi pukao hvata se NullPointerExcpetion a descriptio
				                                              		// se setuje na default vrednost "No description".
			}
				
			//Dodavanje grada 
			event.setCity(city);
			events.add(event);
		}
		
		return events;
	}
	
	
}
