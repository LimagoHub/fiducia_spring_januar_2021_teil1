package de.fiducia.myfirstspring.controllers;

import java.util.List;

import org.mapstruct.Mapper;

import de.fiducia.myfirstspring.repositories.models.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

	Person convert(PersonDTO personDTO);
	PersonDTO convert(Person person);
	List<PersonDTO> convert(List<Person> person);
}
