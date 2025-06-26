package com.desafio3.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio3.crud.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
