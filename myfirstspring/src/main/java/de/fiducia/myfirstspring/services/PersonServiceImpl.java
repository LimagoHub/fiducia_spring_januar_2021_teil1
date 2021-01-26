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
			
			checkPerson(person);
			boolean retval = repo.existsById(person.getId());
			person = repo.save(person);  // Immer mit retval weiter arbeiten

			return  retval;
			
			//person.setVorname("Erika");;
			
			
			
		} catch (RuntimeException e) {
			throw new PersonServiceException("Upps", e);
		}
		
	}


	private void checkPerson(Person person) throws PersonServiceException {
		validatePerson(person);
		businesCheck(person);
	}


	private void businesCheck(Person person) throws PersonServiceException {
		if(antipathen.contains(person.getVorname()))
			throw new PersonServiceException("Antipath");
	}


	private void validatePerson(Person person) throws PersonServiceException {
		if(person == null) 
			throw new PersonServiceException("Person darf nicht null sein");
		if(person.getVorname() == null || person.getVorname().length() < 2)
			throw new PersonServiceException("Vorname muss min. 2 Zeichen enthalten.");
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
