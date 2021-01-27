package de.fiducia.myfirstspring.controllers;


import java.util.List;

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

import de.fiducia.myfirstspring.controllers.DTO.SchweinDTO;
import de.fiducia.myfirstspring.controllers.mapper.SchweinMapper;
import de.fiducia.myfirstspring.services.SchweinService;
import de.fiducia.myfirstspring.services.domainobjects.Schwein;

@RestController
@RequestMapping("/v1/schweine")
public class SchweinController {
	
	
	private final SchweinService service;
	private final SchweinMapper mapper;
	
	
	public SchweinController(SchweinService service, SchweinMapper mapper) {
		
		this.service = service;
		this.mapper = mapper;
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SchweinDTO> getSchweinByID(@PathVariable String id) {
		return ResponseEntity.of(service.ladeSchweinNachId(id).map(mapper::convert));
	}

//	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<SchweinDTO>> getSchweineByName() {
//		return ResponseEntity.ok(mapper.convert(service.ladeAlleSchweine()));
//	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SchweinDTO>> getSchweineByName(@RequestParam(required = false) String name) {
		List<Schwein> retval = name == null ? service.ladeAlleSchweine(): service.ladeSchweinNachName(name);
		return ResponseEntity.ok(mapper.convert(retval));
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> speichern(@Valid @RequestBody SchweinDTO dto) {
		HttpStatus status = service.speichern(mapper.convert(dto))?HttpStatus.OK:HttpStatus.CREATED;
		return ResponseEntity.status(status).build();
		
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> loeschen(@PathVariable String id) {
		if(service.loeschen(id))
			return ResponseEntity.ok().build();
		return ResponseEntity.notFound().build();
		
	}

	@PostMapping(path = "/{id}/fuettern")
	public ResponseEntity<Void> fuettern(@PathVariable String id) {
		
		service.fuettern(id);
		
		return ResponseEntity.ok().build();
		
	}

}
