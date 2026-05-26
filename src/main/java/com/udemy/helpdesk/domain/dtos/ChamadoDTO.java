package com.udemy.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.helpdesk.domain.Chamado;

import jakarta.validation.constraints.NotNull;

public class ChamadoDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataFechamento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAtualizacao;
	
	@NotNull(message="O Campo TECNICO é requerido")
	private Integer tecnicoId;
	
	@NotNull(message="O Campo CLIENTE é requerido")
	private Integer clienteId;	
	
	@NotNull(message="O Campo PRIORIDADE é requerido")
	private Integer prioridadeId;
	
	@NotNull(message="O Campo STATUS é requerido")
	private Integer statusId;
	
	@NotNull(message="O Campo TITULO é requerido")
	private String titulo;
	
	@NotNull(message="O Campo OBSERVAÇÕES é requerido")
	private String observacoes;
	
	private String nomeTecnico;
	private String nomeCliente;
	
	public ChamadoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChamadoDTO(Chamado chamado) 
	{
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.dataAtualizacao = chamado.getDataAtualizacao();
		this.tecnicoId = chamado.getTecnico().getId();
		this.clienteId = chamado.getCliente().getId();
		this.prioridadeId = chamado.getPrioridade();
		this.statusId = chamado.getStatus();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.nomeCliente = chamado.getCliente().getNome();
		this.nomeTecnico = chamado.getTecnico().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Integer getTecnicoId() {
		return tecnicoId;
	}

	public void setTecnicoId(Integer tecnicoId) {
		this.tecnicoId = tecnicoId;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getPrioridadeId() {
		return prioridadeId;
	}

	public void setPrioridadeId(Integer prioridadeId) {
		this.prioridadeId = prioridadeId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
