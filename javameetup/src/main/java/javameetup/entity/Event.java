package javameetup.entity;

import org.springframework.stereotype.Component;

@Component
public class Event {
	
	private long id;
	private String name;
	private String description;
	private City city;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", city=" + city + "]";
	}
	
	
}
