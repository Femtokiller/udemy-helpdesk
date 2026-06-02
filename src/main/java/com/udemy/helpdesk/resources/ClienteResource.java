package com.udemy.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.helpdesk.domain.Cliente;
import com.udemy.helpdesk.domain.dtos.ClienteDTO;
import com.udemy.helpdesk.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource 
{
	@Autowired
	private ClienteService service;
	
	@PreAuthorize("hasAnyRole('TECNICO','CLIENTE','ADMIN')")
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id)
	{
		Cliente cliente = this.service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}
	
	@PreAuthorize("hasAnyRole('TECNICO','ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll()
	{
		List<Cliente> clienteList = this.service.findAll();		
		List<ClienteDTO> clienteDtoList = clienteList.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());		
		return ResponseEntity.ok().body(clienteDtoList);
	}
	
	@PreAuthorize("hasAnyRole('TECNICO','ADMIN')")
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@RequestBody @Valid ClienteDTO clienteRequest)
	{
		Cliente cliente = this.service.create(clienteRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PreAuthorize("hasAnyRole('CLIENTE','ADMIN')")
	@PutMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> update(@RequestBody @Valid ClienteDTO clienteRequest, @PathVariable Integer id)
	{
		Cliente cliente = this.service.update(id, clienteRequest);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> delete( @PathVariable Integer id)
	{
		this.service.delete(id);
		return ResponseEntity.noContent().build();	
	}
}
