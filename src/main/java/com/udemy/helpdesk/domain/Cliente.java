package com.udemy.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udemy.helpdesk.domain.dtos.ClienteDTO;
import com.udemy.helpdesk.domain.enums.Perfil;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("cliente")
public class Cliente extends Pessoa
{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}
	
	public Cliente(ClienteDTO clienteDto) {
		super();
		this.id = clienteDto.getId();
		this.cpf = clienteDto.getCpf();
		this.email = clienteDto.getEmail();
		this.dataCriacao = clienteDto.getDataCriacao();
		this.nome = clienteDto.getNome();
		this.senha = clienteDto.getSenha();
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

}
