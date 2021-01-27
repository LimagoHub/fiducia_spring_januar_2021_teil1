package de.fiducia.myfirstspring.services;

import java.util.List;
import java.util.Optional;

import de.fiducia.myfirstspring.services.domainobjects.Schwein;

public interface SchweinService {

	Optional<Schwein> ladeSchweinNachId(String id);
	List<Schwein> ladeSchweinNachName(String name);
	List<Schwein> ladeAlleSchweine();


	boolean speichern(Schwein schwein);
	boolean loeschen(String id);

	// Nicht idempotent
	void fuettern(String id);

	// idempotent
	void fuettern(Schwein schwein);

	
}