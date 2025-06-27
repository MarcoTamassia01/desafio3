package com.desafio3.crud.controllers;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio3.crud.dtos.ClientDTO;
import com.desafio3.crud.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientService clientService;
	
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>>findAll(Pageable pageable){
		logger.info("Buscando todos os clientes");
		Page<ClientDTO> lista = clientService.findAll(pageable);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO>findById(@PathVariable Long id){
		logger.info("Buscando o cliente com o id: {}",id);
		ClientDTO result = clientService.findById(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO>created(@Valid @RequestBody ClientDTO clientDTO){
		logger.info("Criando um novo cliente com o nome: {}",clientDTO.getName());
		ClientDTO result = clientService.created(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(uri).body(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO>update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO){
		logger.info("Atualizando o cliente com o id: {}",id);
		ClientDTO resultUpdated = clientService.update(id, clientDTO);
		return ResponseEntity.ok(resultUpdated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		logger.warn("Tentando deletar o client com o id: {}",id);
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
