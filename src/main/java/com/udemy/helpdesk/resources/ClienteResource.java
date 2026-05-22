package com.udemy.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.helpdesk.domain.Cliente;
import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.dtos.ClienteDTO;
import com.udemy.helpdesk.domain.dtos.TecnicoDTO;
import com.udemy.helpdesk.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource 
{
	@Autowired
	private ClienteService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id)
	{
		Cliente cliente = this.service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll()
	{
		List<Cliente> clienteList = this.service.findAll();		
		List<ClienteDTO> clienteDtoList = clienteList.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());		
		return ResponseEntity.ok().body(clienteDtoList);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteRequest)
	{
		Cliente cliente = this.service.create(clienteRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
}
