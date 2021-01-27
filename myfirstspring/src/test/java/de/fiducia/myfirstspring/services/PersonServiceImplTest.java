package de.fiducia.myfirstspring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.fiducia.myfirstspring.repositories.PersonRepository;
import de.fiducia.myfirstspring.repositories.models.Person;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
	
	@InjectMocks
	private PersonServiceImpl objectUnderTest;
	 
	@Mock
	private List<String> antipathenMock;
	
	@Mock
	private PersonRepository repoMock;
	
	@Captor
	ArgumentCaptor<Person> personCaptor;
	
	
	private final Person validPerson = Person.builder().id("012345678901234567890123456789012345").vorname("John").nachname("Doe").build();

	@Test
	void speichern_parameterIsNull_throwsPersonenServiceException() {
		PersonServiceException ex = assertThrows(PersonServiceException.class, ()->objectUnderTest.speichern(null));
		assertEquals("Parameter darf nicht null sein.", ex.getMessage());
	} 

	@Test
	void speichern_vornameNull_throwsPersonenServiceException() {
		final Person person = Person.builder().id("012345678901234567890123456789012345").vorname(null).nachname("Doe").build();
		PersonServiceException ex = assertThrows(PersonServiceException.class, ()->objectUnderTest.speichern(person));
		assertEquals("Vorname muss min. 2 Zeichen enthalten.", ex.getMessage());
	} 

	@Test
	void speichern_vornamezuKurz_throwsPersonenServiceException() {
		final Person person = Person.builder().id("012345678901234567890123456789012345").vorname("J").nachname("Doe").build();
		PersonServiceException ex = assertThrows(PersonServiceException.class, ()->objectUnderTest.speichern(person));
		assertEquals("Vorname muss min. 2 Zeichen enthalten.", ex.getMessage());
	} 

	@Test
	void speichern_Antipath_throwsPersonenServiceException() {
		
		when(antipathenMock.contains(anyString())).thenReturn(true);
		
		PersonServiceException ex = assertThrows(PersonServiceException.class, ()->objectUnderTest.speichern(validPerson));
		assertEquals("Antipath", ex.getMessage());
	} 

	
	@Test
	void speichern_ExceptionInUnderLyingService_throwsPersonenServiceException() {
		
		when(antipathenMock.contains(anyString())).thenReturn(false);
		when(repoMock.save(any())).thenThrow(new ArrayIndexOutOfBoundsException());
		
		PersonServiceException ex = assertThrows(PersonServiceException.class, ()->objectUnderTest.speichern(validPerson));
		assertEquals("Service nicht erreichbar.", ex.getMessage());
	} 

	@Test
	void speichern_ValidCall_PersonIsSavedInRepository() throws Exception {
		
		when(antipathenMock.contains(anyString())).thenReturn(false);
		when(repoMock.save(any())).thenReturn(validPerson);
		
		objectUnderTest.speichern(validPerson);
		
		//verify(repoMock, times(1)).save(validPerson);
		InOrder inOrder = inOrder(antipathenMock, repoMock);

		//following will make sure that firstMock was called before secondMock
		inOrder.verify(antipathenMock).contains(validPerson.getVorname());
		inOrder.verify(repoMock, times(1)).save(validPerson);
		
		
	} 

	
	@Test
	void speichern_Spezial() throws Exception {
		
		when(antipathenMock.contains(anyString())).thenReturn(false);
		when(repoMock.save(any())).thenReturn(validPerson);
		
		objectUnderTest.speichern(validPerson.getId(),validPerson.getVorname(),validPerson.getNachname());
		
		//verify(repoMock, times(1)).save(validPerson);
		InOrder inOrder = inOrder(antipathenMock, repoMock);

		//following will make sure that firstMock was called before secondMock
		inOrder.verify(antipathenMock).contains(validPerson.getVorname());
		inOrder.verify(repoMock, times(1)).save(personCaptor.capture());
		
		assertEquals(validPerson.getId(), personCaptor.getValue().getId());
		assertEquals(validPerson.getVorname(), personCaptor.getValue().getVorname());
		assertEquals(validPerson.getNachname(),personCaptor.getValue().getNachname());
		
	} 

	

}
