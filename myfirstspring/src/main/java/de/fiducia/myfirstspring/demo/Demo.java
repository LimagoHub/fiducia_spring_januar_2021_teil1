package de.fiducia.myfirstspring.demo;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fiducia.myfirstspring.repositories.PersonRepository;
import de.fiducia.myfirstspring.repositories.models.Person;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Lazy
public class Demo {
	
	
	private final Dependency dependency;
	private final PersonRepository repo;
	
	@Autowired
	public Demo(final Dependency dependency, final PersonRepository repo) {
		this.dependency =dependency;
		this.repo = repo;
		System.out.println(dependency.foo("Ctor Demo"));
	}
	

	@PostConstruct
	public void init() {
		System.out.println(dependency.foo("Post Contruct"));
		
//		Person p = new Person();
//		p.setId(UUID.randomUUID().toString());
//		p.setVorname("Jane");
//		p.setNachname("Doe");
//		
//		repo.save(p);
		
	}
	
	@PreDestroy
	public void dispose() {
		System.out.println(dependency.foo("..und tschüss..."));
	}
}
