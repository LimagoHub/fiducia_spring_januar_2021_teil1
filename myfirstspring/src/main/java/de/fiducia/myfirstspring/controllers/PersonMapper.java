package de.fiducia.myfirstspring.controllers;

import java.util.List;

import de.fiducia.myfirstspring.repositories.models.Person;


public interface PersonMapper {

	Person convert(PersonDTO personDTO);
	PersonDTO convert(Person person);
	List<PersonDTO> convert(List<Person> person);
}
