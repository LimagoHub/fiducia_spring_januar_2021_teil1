package de.fiducia.myfirstspring.repositories.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name ="tblSchweine")
public class SchweinEntity {
	
	@Id
	
	@Column(length = 36, nullable = false) private String id;
	@Column(length = 30, nullable = false) private String name;
	@Version private LocalDateTime version;
	private int gewicht;

}
