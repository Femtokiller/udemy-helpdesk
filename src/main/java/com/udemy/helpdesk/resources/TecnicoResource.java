package com.udemy.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.dtos.TecnicoDTO;
import com.udemy.helpdesk.services.TecnicoService;

import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/tecnicos")
public class TecnicoResource 
{
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id)
	{
		Tecnico tecnico = this.service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll()
	{
		List<Tecnico> tecnicoList = this.service.findAll();
		
		List<TecnicoDTO> tecnicoDtoList = tecnicoList.stream().map(tecnico -> new TecnicoDTO(tecnico)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(tecnicoDtoList);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@RequestBody @Valid TecnicoDTO tecnicoRequest)
	{
		Tecnico tecnico = this.service.create(tecnicoRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<TecnicoDTO> update(@RequestBody @Valid TecnicoDTO tecnicoRequest, @PathVariable Integer id)
	{
		Tecnico tecnico = this.service.update(id, tecnicoRequest);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<TecnicoDTO> delete( @PathVariable Integer id)
	{
		this.service.delete(id);
		return ResponseEntity.noContent().build();	
	}
}
