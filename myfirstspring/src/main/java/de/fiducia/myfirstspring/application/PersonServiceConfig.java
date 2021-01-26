package de.fiducia.myfirstspring.application;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import de.fiducia.myfirstspring.repositories.PersonRepository;
import de.fiducia.myfirstspring.services.PersonService;
import de.fiducia.myfirstspring.services.PersonServiceImpl;

@Configuration
public class PersonServiceConfig {

	
	@Bean
	@Qualifier("antipathen")
	public List<String> antipathen() {
		return Arrays.asList("Attila","Peter","Paul","Mary");
	}

	@Bean
	@Qualifier("fruits")
	public List<String> fruits() {
		return Arrays.asList("Banana","Apple","Strawerry","Raspberry");
	}

//	@Bean
//	@Primary
//	public List<String> dinge() {
//		return Arrays.asList("Banana","Apple","Strawerry","Raspberry");
//	}
	@Bean
	public PersonService createPersonService(PersonRepository repo,@Qualifier("antipathen") List<String> antipathen) {
		return new PersonServiceImpl(repo, antipathen);
	}

}
