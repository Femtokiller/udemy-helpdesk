package com.udemy.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.helpdesk.domain.Tecnico;
import com.udemy.helpdesk.domain.enums.Perfil;

import jakarta.validation.constraints.NotNull;

public class TecnicoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	
	@NotNull(message="O Campo CPF é requerido")
	protected String cpf;
	
	@NotNull(message="O Campo EMAIL é requerido")
	protected String email;
	
	@NotNull(message="O Campo NOME é requerido")
	protected String nome;
	
	@NotNull(message="O Campo SENHA é requerido")
	protected String senha;
	
	protected Set<Integer> perfis = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDateTime dataCriacao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDateTime dataAtualizacao;
	
	public TecnicoDTO() {
		super();
		addPerfil(Perfil.TECNICO);
	}

	public TecnicoDTO(Tecnico tecnico) {
		super();
		this.id = tecnico.getId();
		this.cpf = tecnico.getCpf();
		this.email = tecnico.getEmail();
		//this.perfis = tecnico.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.nome = tecnico.getNome();
		this.senha = tecnico.getSenha();
		this.dataCriacao = tecnico.getDataCriacao();
		this.dataAtualizacao = tecnico.getDataAtualizacao();
		addPerfil(Perfil.TECNICO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		
		return perfis.stream().map(perfil -> Perfil.toEnum(perfil)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}
