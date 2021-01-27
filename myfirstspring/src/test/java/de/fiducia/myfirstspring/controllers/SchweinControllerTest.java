package de.fiducia.myfirstspring.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.fiducia.myfirstspring.controllers.DTO.SchweinDTO;
import de.fiducia.myfirstspring.controllers.mapper.SchweinMapper;
import de.fiducia.myfirstspring.services.SchweinService;
import de.fiducia.myfirstspring.services.domainobjects.Schwein;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql({"create.sql","insert.sql"})
@ExtendWith(SpringExtension.class)
public class SchweinControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
//	@MockBean
//	private SchweinRepository repoMock;
	@MockBean
	private SchweinService serviceMock;
	
	@Autowired
	private SchweinMapper mapper;
	
	@Test
	public void test1() {
		
//		SchweinEntity entity = SchweinEntity.builder().id("1234").name("Fred").build();
//		Mockito.when(repoMock.findById(Mockito.anyString())).thenReturn(Optional.of(entity));
//		
		
		Schwein schwein = Schwein.builder().id("1234").name("Fred").build();
		Mockito.when(serviceMock.ladeSchweinNachId(Mockito.anyString())).thenReturn(Optional.of(schwein));
		
		SchweinDTO hodge = restTemplate.getForObject("/v1/schweine/c9879a70-db51-424b-828b-cb6b9aed7ba5", SchweinDTO.class);
		assertEquals("Fred", hodge.getName());
	}

	
	@Test
	@Sql({"create.sql","insert.sql"})
	public void test2() {
		
		
		
		Mockito.when(serviceMock.ladeSchweinNachId(Mockito.anyString())).thenReturn(Optional.empty());
		
		ResponseEntity<SchweinDTO> response = restTemplate.getForEntity("/v1/schweine/c9879a70-db51-424b-828b-cb6b9aed7ba5", SchweinDTO.class);
		
//		SchweinDTO hodge = response.getBody();
//		
//		assertEquals("Fred", hodge.getName());
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
	}

	
	@Test
	public void test3() {
		
		SchweinDTO schweinDto = SchweinDTO.builder().id("c9879a70-db51-424b-828b-cb6b9aed7ba5").name("ToSave").gewicht(10).version(LocalDateTime.now()).build();
		HttpEntity<SchweinDTO> entity = new HttpEntity<SchweinDTO>(schweinDto);
		
		when(serviceMock.speichern(any())).thenReturn(true);
		
		ResponseEntity<Void> response = restTemplate.exchange("/v1/schweine", HttpMethod.PUT, entity, Void.class);
	
		assertEquals(HttpStatus.OK, response.getStatusCode()); 
		Schwein schwein = mapper.convert(schweinDto);
		
		verify(serviceMock).speichern(schwein);
		
		
		
	}
	
	@Test
	public void test4() {
		
		SchweinDTO schweinDto = SchweinDTO.builder().id("c9879a70-db51-424b-828b-cb6b9aed7ba5").name("ToSave").gewicht(10).version(LocalDateTime.now()).build();
		HttpEntity<SchweinDTO> entity = new HttpEntity<SchweinDTO>(schweinDto);
		
		when(serviceMock.speichern(any())).thenReturn(false);
		
		ResponseEntity<Void> response = restTemplate.exchange("/v1/schweine", HttpMethod.PUT, entity, Void.class);
	
		assertEquals(HttpStatus.CREATED, response.getStatusCode()); 
		Schwein schwein = mapper.convert(schweinDto);
		
		verify(serviceMock).speichern(schwein);
		
		
		
	}
	
	
	
	@Test
	public void test5() {
		
		
		
		when(serviceMock.loeschen(anyString())).thenReturn(false);
		
		ResponseEntity<Void> response = restTemplate.exchange("/v1/schweine/c9879a70-db51-424b-828b-cb6b9aed7ba5", HttpMethod.DELETE, null, Void.class);
	
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
		
		
		verify(serviceMock).loeschen("c9879a70-db51-424b-828b-cb6b9aed7ba5");
		
		
		
	}
	
	
	@Test
	public void test6() {
		
		
		
		when(serviceMock.loeschen(anyString())).thenReturn(true);
		
		ResponseEntity<Void> response = restTemplate.exchange("/v1/schweine/c9879a70-db51-424b-828b-cb6b9aed7ba5", HttpMethod.DELETE, null, Void.class);
	
		assertEquals(HttpStatus.OK, response.getStatusCode()); 
		
		
		verify(serviceMock).loeschen("c9879a70-db51-424b-828b-cb6b9aed7ba5");
		
		
		
	}
	
	

	


	


}
