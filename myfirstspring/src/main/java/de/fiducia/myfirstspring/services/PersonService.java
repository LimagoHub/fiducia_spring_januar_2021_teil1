package de.fiducia.myfirstspring.services;

import java.util.List;
import java.util.Optional;

import de.fiducia.myfirstspring.repositories.models.Person;

public interface PersonService {

	boolean speichern(Person person) throws PersonServiceException;
	boolean loesche(String id) throws PersonServiceException;
	Optional<Person> ladePersonNachId(String id) throws PersonServiceException;
	List<Person> ladePersonenNachVorname(String vorname) throws PersonServiceException;

}