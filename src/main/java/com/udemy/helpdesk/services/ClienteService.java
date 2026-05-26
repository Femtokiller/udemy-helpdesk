package com.udemy.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.helpdesk.domain.Cliente;
import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.dtos.ClienteDTO;
import com.udemy.helpdesk.repositories.ClienteRepository;
import com.udemy.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService 
{
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	public Cliente findById(Integer id)
	{
		Optional<Cliente> cliente = clienteRepository.findById(id);		
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO clienteRequest) {
		clienteRequest.setId(null);
		pessoaService.validaCpf(clienteRequest.getCpf(), clienteRequest.getId());
		pessoaService.validaEmail(clienteRequest.getEmail(), clienteRequest.getId());
		Cliente cliente = new Cliente(clienteRequest);
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO clienteRequest) 
	{
		clienteRequest.setId(id);
		Cliente cliente = findById(id);
		LocalDate dataCriacao = cliente.getDataCriacao(); 
		
		if(!clienteRequest.getCpf().isEmpty())
			pessoaService.validaCpf(clienteRequest.getCpf(), id);
		
		if(clienteRequest.getEmail().isEmpty())
			pessoaService.validaEmail(clienteRequest.getEmail(), id);
		
		cliente = new Cliente(clienteRequest);
		cliente.setDataCriacao(dataCriacao);
		return clienteRepository.save(cliente);
	}

	public void delete(Integer id)
	{
		Cliente cliente = findById(id);
		
		if(cliente.getChamados().size() > 0)
			throw new DataIntegrityViolationException("Cliente contém ordens de serviço e não pode ser deletado!");
		
		clienteRepository.deleteById(id);		
	}
}
