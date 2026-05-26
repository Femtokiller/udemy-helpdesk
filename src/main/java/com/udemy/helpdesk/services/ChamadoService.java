package com.udemy.helpdesk.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.helpdesk.domain.Chamado;
import com.udemy.helpdesk.domain.Cliente;
import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.dtos.ChamadoDTO;
import com.udemy.helpdesk.repositories.ChamadoRepository;
import com.udemy.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.udemy.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadoService 
{
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Integer id)
	{
		Optional<Chamado> chamado = chamadoRepository.findById(id);		
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! ID: " + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO chamadoRequest) 
	{
		chamadoRequest.setId(null);
		chamadoRequest.setDataAbertura(LocalDateTime.now());
		
		return chamadoRepository.save(novochamado(chamadoRequest));
	}

	public Chamado update(Integer id, @Valid ChamadoDTO chamadoRequest) 
	{
		Chamado chamado = findById(id);
		chamadoRequest.setId(id);
		chamadoRequest.setDataAbertura(chamado.getDataAbertura());

		if(chamadoRequest.getStatusId().equals(2))
			chamadoRequest.setDataFechamento(LocalDateTime.now());
		
		return chamadoRepository.save(novochamado(chamadoRequest));
	}
	
	private Chamado novochamado(ChamadoDTO chamadoRequest) 
	{
		Tecnico tecnico = tecnicoService.findById(chamadoRequest.getTecnicoId());
		Cliente cliente = clienteService.findById(chamadoRequest.getClienteId());
		
		Chamado chamado = new Chamado(chamadoRequest);
		chamado.setCliente(cliente);
		chamado.setTecnico(tecnico);
		chamado.setDataAtualizacao(LocalDateTime.now());
		
		return chamado;
	}

	public void delete(Integer id) 
	{
		Chamado chamado = findById(id);
		
		if(chamado.getTecnico() != null)
			throw new DataIntegrityViolationException("Ordem de serviço contém técnico associado e não pode ser deletado!");
		
		if(chamado.getCliente() != null)
			throw new DataIntegrityViolationException("Ordem de serviço contém cliente associado e não pode ser deletado!");
				
		chamadoRepository.deleteById(id);
	}
	

}
