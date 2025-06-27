package com.desafio3.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.desafio3.crud.dtos.ClientDTO;
import com.desafio3.crud.entities.Client;
import com.desafio3.crud.repositories.ClientRepository;
import com.desafio3.crud.services.exceptions.DataBaseException;
import com.desafio3.crud.services.exceptions.RequiredObjectIsNullException;
import com.desafio3.crud.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable){
		Page<Client> result = clientRepository.findAll(pageable);
		return result.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client entity = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possivel encontrar um cliente com o id: "+id));
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO created(ClientDTO dto) {
		if(dto==null) {
			throw new RequiredObjectIsNullException("Objeto requerido está nulo!");
		}
		Client entity =new Client();		
		copyDtoToClient(dto,entity);
		entity =clientRepository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		//Find By ID busca no BD antes, getRerenceById Tenta alterar sem saber se existe
		Client entity = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possivel encontrar um cliente com o id: "+id));
		copyDtoToClient(dto,entity);
		Client entityUpdated = clientRepository.save(entity);
		return new ClientDTO(entityUpdated);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		
		if(!clientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Não foi possivel encontrar um cliente com o id: "+id);
		}

		try {
			clientRepository.deleteById(id);
		}catch(DataBaseException e) {
			throw new DataBaseException("O sistema informa que não pode fazer a deleção pois viola a integridade referencial dos dados!");
		}
		
	}
	
	public void copyDtoToClient(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
	
	
	
}
