package com.udemy.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.helpdesk.domain.Chamado;
import com.udemy.helpdesk.domain.Cliente;
import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.enums.Perfil;
import com.udemy.helpdesk.domain.enums.Prioridade;
import com.udemy.helpdesk.domain.enums.Status;
import com.udemy.helpdesk.repositories.ChamadoRepository;
import com.udemy.helpdesk.repositories.ClienteRepository;
import com.udemy.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner 
{
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
		
		System.out.println("Ola mundo");
	}

	@Override
	public void run(String... args) throws Exception 
	{
		Tecnico tec1 = new Tecnico(null, "Valdir", "01775995100", "valdir@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus", "54825436765", "turvals@mail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}

	
}
