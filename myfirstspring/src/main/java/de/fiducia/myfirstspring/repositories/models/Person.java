package de.fiducia.myfirstspring.repositories.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tblPersonen")
public class Person {
	
	@Id
	@Column(length = 36)
	private String id;
	
	@Column(length = 30)
	private String vorname;
	
	@Column(length = 30, nullable = false)
	private String nachname;

//	@Column(name= "age")
//	private int alter;
}
