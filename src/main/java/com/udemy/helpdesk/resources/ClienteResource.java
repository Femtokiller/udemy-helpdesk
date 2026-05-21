package com.udemy.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.helpdesk.domain.Cliente;
import com.udemy.helpdesk.domain.dtos.ClienteDTO;
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
		Cliente obj = this.service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
}
