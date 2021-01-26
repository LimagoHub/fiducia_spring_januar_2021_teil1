package de.fiducia.myfirstspring.controllers.DTO;

import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinDTO {
	
	@NotNull
	@Size(min = 36, max = 36)
	private String id;
	
	@NotBlank
	@Size(max = 36)
	private String name;
	@NotNull
	private LocalDateTime version;
	@DecimalMin(value = "10")
	private int gewicht;
}
