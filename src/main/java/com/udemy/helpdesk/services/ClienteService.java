package com.udemy.helpdesk.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

	public Cliente create(@Valid ClienteDTO clienteRequest) 
	{
		clienteRequest.setId(null);
		clienteRequest.setDataCriacao(LocalDateTime.now());
		
		return clienteRepository.save(novoCliente(clienteRequest));
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO clienteRequest) 
	{
		Cliente cliente = findById(id);
		clienteRequest.setId(id);		
		clienteRequest.setDataCriacao(cliente.getDataCriacao()); 
		
		return clienteRepository.save(novoCliente(clienteRequest));
	}
	
	private Cliente novoCliente(ClienteDTO clienteRequest) 
	{
		pessoaService.validaCpf(clienteRequest.getCpf(), clienteRequest.getId());
		pessoaService.validaEmail(clienteRequest.getEmail(), clienteRequest.getId());
		
		Cliente cliente = new Cliente(clienteRequest);
		cliente.setDataAtualizacao(LocalDateTime.now());
		
		return cliente;
	}

	public void delete(Integer id)
	{
		Cliente cliente = findById(id);
		
		if(cliente.getChamados().size() > 0)
			throw new DataIntegrityViolationException("Cliente contém ordens de serviço e não pode ser deletado!");
		
		clienteRepository.deleteById(id);		
	}
}
