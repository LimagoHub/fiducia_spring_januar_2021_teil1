package de.fiducia.myfirstspring.services.domainobjects;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
public class Schwein {
	
	private String id;
	private String name;
	private LocalDateTime version;
	@Setter(value = AccessLevel.PRIVATE)
	private int gewicht;
	
	public void setName(String name) {
		if(name == null || name.equals("Elsa")) throw new IllegalArgumentException("Name nicht erlaubt");
		this.name = name;
	}
	
	public void fressen() {
		gewicht ++;
	}

}
