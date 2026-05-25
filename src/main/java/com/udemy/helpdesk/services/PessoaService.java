package com.udemy.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.helpdesk.domain.Pessoa;
import com.udemy.helpdesk.repositories.PessoaRepository;
import com.udemy.helpdesk.services.exceptions.DataIntegrityViolationException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public void validaCpf(String cpf, Integer id) 
	{
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);
		
		if(pessoa.isPresent() && pessoa.get().getId() != id) 
			throw new DataIntegrityViolationException("CPF " + cpf + " já cadastrado no sistema!");
	
	}
		
	public void validaEmail(String email, Integer id) 
	{
		List<Pessoa> pessoaList = pessoaRepository.findByEmail(email);
		
		if(!pessoaList.isEmpty() && pessoaList.stream().allMatch(pessoa -> pessoa.getId() != id)) 
			throw new DataIntegrityViolationException("Email " + email + " já cadastrado no sistema!");
	
	}
}
