package de.fiducia.myfirstspring.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.fiducia.myfirstspring.repositories.models.Person;
import de.fiducia.myfirstspring.services.PersonService;
import de.fiducia.myfirstspring.services.PersonServiceException;


@Component
@Transactional
public class PersonenServiceHandlerImpl {

	private final PersonService service;

	public PersonenServiceHandlerImpl(final PersonService service) {
		
		this.service = service;
	}
	
	public void handlePersonErfassen(Person person) throws PersonServiceException{
		try {
			service.speichern(person);
			// Event feuern
		} catch (PersonServiceException e) {
			// Anderen Event feuern
			throw e;
		}
	}

}
