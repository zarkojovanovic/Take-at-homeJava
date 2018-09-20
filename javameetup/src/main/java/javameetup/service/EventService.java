package javameetup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javameetup.entity.City;
import javameetup.entity.Event;
import javameetup.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	//Metoda se obraca repozitorijum i vraca listu dogadjaja
	public List<Event> getAll(Object obj, City city) {
		return eventRepository.getAll(obj, city);
	}
}
