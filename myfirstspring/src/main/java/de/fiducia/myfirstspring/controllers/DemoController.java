package de.fiducia.myfirstspring.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import de.fiducia.myfirstspring.controllers.DTO.PersonDTO;
import de.fiducia.myfirstspring.controllers.mapper.PersonMapper;
import de.fiducia.myfirstspring.services.PersonService;
import de.fiducia.myfirstspring.services.PersonServiceException;



@RestController
@RequestMapping(DemoController.V1_PERSONS)
public class DemoController {
	
	
	static final String V1_PERSONS = "/v1/persons";

	private final PersonService personService;
	private final PersonMapper personMapper;
	


	public DemoController(final PersonService personService, final PersonMapper personMapper) {
		this.personService = personService;
		this.personMapper  = personMapper;
	}

	@GetMapping(path="/gruss", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getGreeting() {
		return "Hello World";
	}

	@GetMapping(path="/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> getPersonById(@PathVariable(name="id") String id) throws PersonServiceException {
		
		
		
		return ResponseEntity
				.of(personService.ladePersonNachId(id).map(personMapper::convert));
	}
	
	@GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonDTO>> getPersonByFirstname(@RequestParam String vorname) throws PersonServiceException {
		
		return ResponseEntity.ok(personMapper.convert(personService.ladePersonenNachVorname(vorname)));
	}

	// Post als Get-Ersatz weil wir ein Parameterobjekt verwenden Safe=true idempotent=true
	@PostMapping(path="/convertToUpper",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> toUpper(@RequestBody PersonDTO p) {
		
		p.setId(p.getId().toUpperCase());
		p.setVorname(p.getVorname().toUpperCase());
		p.setNachname(p.getNachname().toUpperCase());
		
		return ResponseEntity.ok(p);
	}
	
	@DeleteMapping(path="/person/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name="id") String id) throws PersonServiceException {
		if(personService.loesche(id))
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping(path="/person",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saveOrUpdate(@Valid @RequestBody PersonDTO person) throws PersonServiceException {
		
		
		
		HttpStatus status = personService.speichern(personMapper.convert(person))? HttpStatus.OK: HttpStatus.CREATED;
		
		return ResponseEntity.status(status).build();
	}



	@PostMapping(path="/person",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saveNotIdempotent(@Valid @RequestBody PersonDTO person,  UriComponentsBuilder b)  {
		person.setId(UUID.randomUUID().toString());
		System.out.println("Person " + person + " wird gespeichert!" );
		
		// throws Exception
		
		
		//throw new PersonenServiceException("Upps");
		UriComponents uriComponents = b.path("v1/persons/person/{id}").buildAndExpand(person.getId());
		return ResponseEntity.created(uriComponents.toUri()).build();
	}





}
