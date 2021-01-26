package de.fiducia.myfirstspring.services;

import de.fiducia.myfirstspring.repositories.models.Person;

public interface PersonService {

	void speichern(Person person) throws PersonServiceException;

}