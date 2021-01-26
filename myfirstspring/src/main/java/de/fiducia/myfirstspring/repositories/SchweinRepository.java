package de.fiducia.myfirstspring.repositories;

import org.springframework.data.repository.CrudRepository;

import de.fiducia.myfirstspring.repositories.models.SchweinEntity;

public interface SchweinRepository extends CrudRepository<SchweinEntity, String> {

}
