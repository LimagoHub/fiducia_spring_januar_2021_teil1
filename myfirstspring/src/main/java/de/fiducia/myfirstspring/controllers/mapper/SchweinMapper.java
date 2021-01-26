package de.fiducia.myfirstspring.controllers.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import de.fiducia.myfirstspring.controllers.DTO.SchweinDTO;
import de.fiducia.myfirstspring.services.domainobjects.Schwein;

@Mapper(componentModel = "spring")
public interface SchweinMapper {

	Schwein convert(SchweinDTO schweinDTO);
	SchweinDTO convert(Schwein schwein);
	List<SchweinDTO> convert(List<Schwein> schweine);
}
