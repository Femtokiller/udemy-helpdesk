package com.udemy.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.dtos.TecnicoDTO;
import com.udemy.helpdesk.repositories.TecnicoRepository;
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

	public Tecnico create(TecnicoDTO tecnicoRequest) 
	{
		tecnicoRequest.setId(null);
		pessoaService.validaCpf(tecnicoRequest.getCpf(), tecnicoRequest.getId());
		pessoaService.validaEmail(tecnicoRequest.getEmail(), tecnicoRequest.getId());
		Tecnico tecnico = new Tecnico(tecnicoRequest);
		return tecnicoRepository.save(tecnico);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoRequest) 
	{
		tecnicoRequest.setId(id);
		Tecnico tecnico = findById(id);
		LocalDate dataCriacao = tecnico.getDataCriacao(); 
		
		if(!tecnicoRequest.getCpf().isEmpty())
			pessoaService.validaCpf(tecnicoRequest.getCpf(), id);
		
		if(tecnicoRequest.getEmail().isEmpty())
			pessoaService.validaEmail(tecnicoRequest.getEmail(), id);
		
		tecnico = new Tecnico(tecnicoRequest);
		tecnico.setDataCriacao(dataCriacao);
		return tecnicoRepository.save(tecnico);
	}
	
}
