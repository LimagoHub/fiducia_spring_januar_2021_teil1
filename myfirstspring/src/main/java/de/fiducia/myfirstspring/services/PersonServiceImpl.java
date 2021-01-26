package de.fiducia.myfirstspring.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.fiducia.myfirstspring.repositories.PersonRepository;
import de.fiducia.myfirstspring.repositories.models.Person;

@Service
@Transactional(
		propagation = Propagation.REQUIRED, 
		rollbackFor = PersonServiceException.class
)


public class PersonServiceImpl implements PersonService {
	
	private final PersonRepository repo;
	
	
	public PersonServiceImpl(final PersonRepository repo) {
		this.repo = repo;
	}
	
	
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void speichern(Person person) throws PersonServiceException{
		try {
			if(person == null) 
				throw new PersonServiceException("Person darf nicht null sein");
			if(person.getVorname() == null || person.getVorname().length() < 2)
				throw new PersonServiceException("Vorname muss min. 2 Zeichen enthalten.");
			
			if("Attila".equals(person.getVorname()))
				throw new PersonServiceException("Antipath");
			
			person = repo.save(person);  // Immer mit retval weiter arbeiten
			
			//person.setVorname("Erika");;
			
			
		} catch (RuntimeException e) {
			throw new PersonServiceException("Upps", e);
		}
		
	}
	

	public void bulkSpeichern(List<Person> persons) throws PersonServiceException{
		for (Person person : persons) {
			speichern(person);
		}
		
	}

}
