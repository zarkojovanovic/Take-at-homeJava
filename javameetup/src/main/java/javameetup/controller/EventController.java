package javameetup.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javameetup.entity.City;
import javameetup.entity.Event;
import javameetup.service.CityService;
import javameetup.service.EventService;

@RestController
public class EventController {

	@Autowired
	EventService eventService;
	
	
	@Autowired
	CityService cityService;
	
	//Obrada GET zahteva za dodadjaje
	@GetMapping(value = "api/events/{cityId}")
	public ResponseEntity<List<Event>> get(@PathVariable Long cityId) throws JsonParseException, JsonMappingException, IOException, ParseException {
		return new ResponseEntity<List<Event>>(getEvents(cityId), HttpStatus.OK);
	}
	
	//Metoda koja vraca listu objekata tipa Event, metoda prima id grada
	public List<Event> getEvents(Long cityId) throws JsonParseException, JsonMappingException, IOException, ParseException {
		
		//Pronalazenje grada po id i zatim uzimanje geografske sirine i duzine tog grada 
	 	City city = cityService.findById(cityId);
		String latitude = city.getLat();
		String longitude = city.getLon();
		
		List<Event> events = new ArrayList<Event>();
		
		 //Na putanju se dodaju atributi latitude i longitude  
		final String uri = "https://api.meetup.com/find/upcoming_events?photo-host=public&page=20&sig_id=263657957&lon="+ longitude+ "&lat="+ latitude +"&sig=98b47b68ff72971d7340756ca465768ffeb0f0fa";
		
		RestTemplate restTemplate = new RestTemplate();
		//Komunikacija sa eksternim apijem vracaju se svi dogadjaji koji se nalaze na datoj 
		//geo. sirini i duzini to jest grada koji se nalazi na to sirini i duzini
		ResponseEntity<String> res = restTemplate.getForEntity(uri, String.class );
		
		Object obj = new JSONParser().parse(res.getBody());
		
		// Servis vraca dogadjaje za navedeni grad
		events = eventService.getAll(obj, city);
		
		return events;
	}
}
