package com.desafio3.crud.dtos;

import java.time.LocalDate;

import com.desafio3.crud.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDTO {
	
	private Long id;
	
	@NotBlank(message = "O nome não pode estar vazio!")
	private String name;
	private String cpf;
	private Double income;
	@PastOrPresent(message = "A data de nascimento não pode ser futura")
	private LocalDate birthDate;
	private Integer children;
	
	public ClientDTO() {
	}

	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.income = entity.getIncome();
		this.birthDate = entity.getBirthDate();
		this.children = entity.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	

}
