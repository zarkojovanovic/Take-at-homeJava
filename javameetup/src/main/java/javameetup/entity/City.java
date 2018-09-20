package javameetup.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class City {
	
	private long id;
	private String name;
	private String lat;
	private String lon;
	//private List<Event> events = new ArrayList<Event>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
/*	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}*/
	@Override
	public String toString() {
		return id + "." + " [id=" + id + ", name=" + name + ", lat=" + lat + ", lon=" + lon + "]";
	}
	
	
	
	
}
