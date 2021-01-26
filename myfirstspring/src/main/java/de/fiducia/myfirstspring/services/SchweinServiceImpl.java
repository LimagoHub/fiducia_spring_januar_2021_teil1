package de.fiducia.myfirstspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.fiducia.myfirstspring.repositories.SchweinRepository;
import de.fiducia.myfirstspring.repositories.models.SchweinEntity;
import de.fiducia.myfirstspring.services.domainobjects.Schwein;
import de.fiducia.myfirstspring.services.mappers.SchweinEntityMapper;

@Service
@Transactional
public class SchweinServiceImpl implements SchweinService {
	
	private final SchweinRepository repo;
	private final SchweinEntityMapper mapper;
	
	public SchweinServiceImpl(SchweinRepository repo, SchweinEntityMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<Schwein> ladeSchweinNachId(String id) {
		return repo.findById(id).map(mapper::convert);
	}
	
	@Override
	public boolean speichern(Schwein schwein) {
		boolean retval = repo.existsById(schwein.getId());
		repo.save(mapper.convert(schwein));
		return retval;
	}

	// Nicht idempotent
	@Override
	public void fuettern(String id) {
		Schwein schwein = ladeSchweinNachId(id).orElseThrow(()->new NoSuchElementException("Upps"));
		schwein.fressen();
		speichern(schwein);
	}

	// idempotent
	@Override
	public void fuettern(Schwein schwein) {
		schwein.fressen();
		speichern(schwein);
	}

	@Override
	public List<Schwein> ladeAlleSchwein() {
		
		List<SchweinEntity> retval = new ArrayList<>();
		repo.findAll().forEach(retval::add);
		
		return mapper.convert(retval);
	}
	
	
}
