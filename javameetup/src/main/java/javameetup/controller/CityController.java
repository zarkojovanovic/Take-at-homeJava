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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javameetup.entity.City;
import javameetup.service.CityService;

@RestController
public class CityController {
	
	@Autowired
	CityService cityService;
	
	
    //Obrada GET zahteva za gradove	
	@GetMapping(value = "api/cities")
	public ResponseEntity<List<City>> get() throws JsonParseException, JsonMappingException, IOException, ParseException {
		return new ResponseEntity<List<City>>(getCities(), HttpStatus.OK);
	}
	
	
    // Metoda koja vraca listu objekata tipa City
	public List<City> getCities() throws JsonParseException, JsonMappingException, IOException, ParseException {
		 List<City> cities = new ArrayList<City>();
		 final String uri = "https://api.meetup.com/2/cities?country=rs&offset=0&format=json&photo-host=public&page=20&radius=50&order=size&desc=false&sig_id=263657957&sig=583aad490af4c41b686e037c0cd8db965a80aed1";
		 RestTemplate restTemplate = new RestTemplate();
		 
		 //Opciono moze se dodati i heder ja sam odlucio za ovu priliku da ga ne dodajem
		 /*HttpHeaders headers = new HttpHeaders();
		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		 HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);*/
		 
		 //Komunikacija sa eksternim apijem
		 ResponseEntity<String> res = restTemplate.getForEntity(uri, String.class);
		 
		 Object obj = new JSONParser().parse(res.getBody()); 
         
		 //Servis vraca gradove 
		 cities = cityService.getAll(obj);
	       
		 return cities;
	}
}
