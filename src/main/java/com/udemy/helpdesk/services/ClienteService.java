package com.udemy.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.helpdesk.domain.Cliente;
import com.udemy.helpdesk.domain.dtos.ClienteDTO;
import com.udemy.helpdesk.repositories.ClienteRepository;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService 
{
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id)
	{
		Optional<Cliente> cliente = repository.findById(id);		
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO clienteRequest) {
		clienteRequest.setId(null);
		Cliente cliente = new Cliente(clienteRequest);
		return repository.save(cliente);
	}
}
