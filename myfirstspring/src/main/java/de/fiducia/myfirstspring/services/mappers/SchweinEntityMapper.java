package de.fiducia.myfirstspring.services.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import de.fiducia.myfirstspring.repositories.models.SchweinEntity;
import de.fiducia.myfirstspring.services.domainobjects.Schwein;

@Mapper(componentModel = "spring")
public interface SchweinEntityMapper {

	SchweinEntity convert (Schwein schwein);
	Schwein convert (SchweinEntity schweinEntity);
	List<Schwein> convert (List<SchweinEntity> schweinEntities);
}
