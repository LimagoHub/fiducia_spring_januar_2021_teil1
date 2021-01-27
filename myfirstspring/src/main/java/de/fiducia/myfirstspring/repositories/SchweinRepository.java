package de.fiducia.myfirstspring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.fiducia.myfirstspring.repositories.models.SchweinEntity;

public interface SchweinRepository extends CrudRepository<SchweinEntity, String> {
	
	List<SchweinEntity> findByName(String name);

}
