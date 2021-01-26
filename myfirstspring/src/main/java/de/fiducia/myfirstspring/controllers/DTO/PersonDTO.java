package de.fiducia.myfirstspring.controllers.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
	
	@NotNull
	@Size(max = 36)
	private String id=null;
	
	@Size(max = 30)
	@NotBlank
	private String vorname="";
	
	@NotNull
	@NotBlank
	@Size(max = 30)
	private String nachname="";

}
