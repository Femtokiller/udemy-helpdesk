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

import com.udemy.helpdesk.domain.Chamado;
import com.udemy.helpdesk.domain.dtos.ChamadoDTO;
import com.udemy.helpdesk.services.ChamadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/chamados")
public class ChamadoResource 
{
	@Autowired
	private ChamadoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id)
	{
		Chamado chamado = this.service.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll()
	{
		List<Chamado> chamadoList = this.service.findAll();
		
		List<ChamadoDTO> chamadoDtoList = chamadoList.stream().map(chamado -> new ChamadoDTO(chamado)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(chamadoDtoList);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create(@RequestBody @Valid ChamadoDTO chamadoRequest)
	{
		Chamado chamado = this.service.create(chamadoRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ChamadoDTO> update(@RequestBody @Valid  ChamadoDTO chamadoRequest, @PathVariable Integer id)
	{
		Chamado chamado = this.service.update(id, chamadoRequest);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ChamadoDTO> delete(@PathVariable Integer id)
	{
		this.service.delete(id);
		return ResponseEntity.noContent().build();	
	}

}
