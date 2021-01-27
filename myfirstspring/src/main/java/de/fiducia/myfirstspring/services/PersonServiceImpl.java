package de.fiducia.myfirstspring.services;

import java.util.List;
import java.util.Optional;

import de.fiducia.myfirstspring.repositories.PersonRepository;
import de.fiducia.myfirstspring.repositories.models.Person;



public class PersonServiceImpl implements PersonService {
	
	private final PersonRepository repo;
	private final List<String> antipathen;
	
	public PersonServiceImpl(final PersonRepository repo,  final List<String> antipathen) {
		this.repo = repo;
		this.antipathen = antipathen;
	}
	
	
	@Override
	public boolean speichern(Person person) throws PersonServiceException{
		
		
		try {
			return speichernImpl(person);
		} catch (RuntimeException e) {
			throw new PersonServiceException("Service nicht erreichbar.", e);
		}
		 
	}

	@Override
	public boolean speichern(String id, String vorname, String nachname) throws PersonServiceException {
		Person p = Person.builder().id(id).vorname(vorname).nachname(nachname).build();
		
		return speichern(p);
	}

	private boolean speichernImpl(Person person) throws PersonServiceException {
		pruefePerson(person);
		repo.save(person);
		return true;
	}


	private void pruefePerson(Person person) throws PersonServiceException {
		if(person == null)
			throw new PersonServiceException("Parameter darf nicht null sein.");
		
		if(person.getVorname() == null || person.getVorname().length() < 2)
			throw new PersonServiceException("Vorname muss min. 2 Zeichen enthalten.");
		
		if(antipathen.contains(person.getVorname()))
			throw new PersonServiceException("Antipath");
	}


	
	

	public void bulkSpeichern(List<Person> persons) throws PersonServiceException{
		for (Person person : persons) {
			speichern(person);
		}
		
	}


	@Override
	public List<Person> ladePersonenNachVorname(String vorname) throws PersonServiceException{
		
		try {
			return repo.findByVorname(vorname);
		} catch (RuntimeException e) {
			throw new PersonServiceException("Upps", e);
		}
	}


	@Override
	public boolean loesche(String id) throws PersonServiceException {
		
		try {
			if( repo.existsById(id)) {
				
				repo.deleteById(id);
				return true;
			}
			return false;
			
		} catch (RuntimeException e) {
			throw new PersonServiceException("Upps", e);
		}
	}


	@Override
	public Optional<Person> ladePersonNachId(String id) throws PersonServiceException {
		
		try {
			return repo.findById(id);
		} catch (RuntimeException e) {
			throw new PersonServiceException("Upps", e);
		}
	}


	
}
