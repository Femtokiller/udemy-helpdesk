package com.udemy.helpdesk.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> 
{
	Optional<Pessoa> findByCpf(String cpf);
	List<Pessoa> findByEmail(String email);	
}
