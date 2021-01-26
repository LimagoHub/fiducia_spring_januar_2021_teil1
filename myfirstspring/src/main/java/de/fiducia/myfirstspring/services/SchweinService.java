package de.fiducia.myfirstspring.services;

import java.util.List;
import java.util.Optional;

import de.fiducia.myfirstspring.services.domainobjects.Schwein;

public interface SchweinService {

	Optional<Schwein> ladeSchweinNachId(String id);

	boolean speichern(Schwein schwein);

	// Nicht idempotent
	void fuettern(String id);

	// idempotent
	void fuettern(Schwein schwein);

	List<Schwein> ladeAlleSchwein();

}