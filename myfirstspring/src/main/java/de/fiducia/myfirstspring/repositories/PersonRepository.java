package de.fiducia.myfirstspring.repositories;

import org.springframework.data.repository.CrudRepository;

import de.fiducia.myfirstspring.repositories.models.Person;

public interface PersonRepository extends CrudRepository<Person, String> {

}
