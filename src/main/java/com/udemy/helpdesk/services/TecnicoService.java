package com.udemy.helpdesk.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.dtos.TecnicoDTO;
import com.udemy.helpdesk.repositories.TecnicoRepository;
import com.udemy.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService 
{
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	public Tecnico findById(Integer id)
	{
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);		
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado! ID: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(@Valid TecnicoDTO tecnicoRequest) 
	{
		tecnicoRequest.setId(null);
		tecnicoRequest.setDataCriacao(LocalDateTime.now());
	
		return tecnicoRepository.save(novoTecnico(tecnicoRequest));
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoRequest) 
	{		
		Tecnico tecnico = findById(id);
		tecnicoRequest.setId(id);
		tecnicoRequest.setDataCriacao(tecnico.getDataCriacao());
		
		return tecnicoRepository.save(novoTecnico(tecnicoRequest));
	}
	
	private Tecnico novoTecnico(TecnicoDTO tecnicoRequest) 
	{
		pessoaService.validaCpf(tecnicoRequest.getCpf(), tecnicoRequest.getId());
		pessoaService.validaEmail(tecnicoRequest.getEmail(), tecnicoRequest.getId());
		
		Tecnico tecnico = new Tecnico(tecnicoRequest);
		tecnico.setDataAtualizacao(LocalDateTime.now());
		
		return tecnico;
	}

	public void delete(Integer id) 
	{
		Tecnico tecnico = findById(id);
		
		if(tecnico.getChamados().size() > 0)
			throw new DataIntegrityViolationException("Técnico contém ordens de serviço e não pode ser deletado!");
		
		tecnicoRepository.deleteById(id);
	}
	
}
