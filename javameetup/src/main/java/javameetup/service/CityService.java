package javameetup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javameetup.entity.City;
import javameetup.repository.CityRepository;



@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;
	
	//Metoda se obraca repozitorijumu i vraca sve gradove 
	public List<City> getAll(Object obj) {
		return cityRepository.getAll(obj); 
	}
	//Metoda se obraca repozitorijumu i vraca grad sa zadatim id-om
	public City findById(Long id) {
		return cityRepository.findById(id);
	}
}
