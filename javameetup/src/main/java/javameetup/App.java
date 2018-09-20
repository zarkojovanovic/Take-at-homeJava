package javameetup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javameetup.entity.City;
import javameetup.entity.Event;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args); // Startovanje spring boot aplikacije
        
        //Klijent
        getAllCities();
        while (true) {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	String decision= "";
        	try {
				getEvents();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	System.out.println("Ponovna pretraga? Da/Ne");
        	try {
				decision =br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	if (decision.equalsIgnoreCase("Ne")) {
        		System.exit(0);
        		break;
        	}
        	else continue;
        }
      
    }
    //Preuzima sve gradove
    public static void getAllCities() {
    	final String uri = "http://localhost:8080/api/cities";
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<City[]> responseEntity = restTemplate.getForEntity(uri, City[].class); //Komunikacija sa REST servisom.
    	City[] cities = responseEntity.getBody();
    	
    	// Ispis gradova
    	for (City city: cities) {															
    		System.out.println(city.toString());											
    	}																					
    
    }
	// Preuzima sve dogadjaje za navedeni grad
    public static void getEvents() throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	 
    	System.out.println("Unesite redni broj grada za koji zelite da vidite dogadjaje:");
    	String option = br.readLine();
    	final String uri = "http://localhost:8080/api/events/" + option; // Na putanju se dodaje id grada koji je korisnik uneo.
    	
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<Event[]> responseEntity;
    	
    	responseEntity = restTemplate.getForEntity(uri, Event[].class); // Komunikacija sa REST servisom.
    	
    	Event[] events = responseEntity.getBody();
    	
    	//Ispis dogadjaj za navedeni grad
    	for (Event event: events) {
    		System.out.println(event.toString());
    	}
    	
    }
}