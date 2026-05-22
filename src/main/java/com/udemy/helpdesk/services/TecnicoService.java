package com.udemy.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.dtos.TecnicoDTO;
import com.udemy.helpdesk.repositories.TecnicoRepository;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService 
{
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id)
	{
		Optional<Tecnico> tecnico = repository.findById(id);		
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado! ID: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoRequest) 
	{
		tecnicoRequest.setId(null);
		Tecnico tecnico = new Tecnico(tecnicoRequest);
		return repository.save(tecnico);
	}
	
	
	

}
