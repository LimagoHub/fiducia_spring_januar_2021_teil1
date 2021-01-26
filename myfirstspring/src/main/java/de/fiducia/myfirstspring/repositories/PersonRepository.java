package de.fiducia.myfirstspring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.fiducia.myfirstspring.repositories.models.Person;
import de.fiducia.myfirstspring.repositories.models.TinyPerson;

public interface PersonRepository extends CrudRepository<Person, String> {
	
	List<Person> findAllPersonsAsList();
	List<Person> findByVorname(String vorname);
	List<Person> findByNachname(String nachname);
	List<Person> findByVornameOrNachname(String vorname, String nachname);
	List<Person> findByVornameAndNachname(String vorname, String nachname); 
	
	
	@Query("select new de.fiducia.myfirstspring.repositories.models.TinyPerson(p.id, p.nachname) from Person p")
	List<TinyPerson> findTinies();

}
