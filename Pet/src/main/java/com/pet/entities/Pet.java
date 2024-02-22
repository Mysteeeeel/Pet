package com.pet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name="pet")
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotBlank
	private String nome;
	

	private String documento;
	
	@NotNull
	@NotBlank
	private String nascimento;

	@NotNull
	@NotBlank
	private String cuidador;

	public Pet(String nome, String nascimento, String cuidador) {
		this.nome = nome;
		this.nascimento = nascimento;
		this.cuidador = cuidador;
	}
}