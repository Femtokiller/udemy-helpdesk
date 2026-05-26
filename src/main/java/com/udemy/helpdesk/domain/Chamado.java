package com.udemy.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.helpdesk.domain.dtos.ChamadoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="chamados", schema = "public")
public class Chamado implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chamados_seq")
	@SequenceGenerator(schema = "public", name="chamados_seq", sequenceName = "chamados_seq", allocationSize = 1)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataFechamento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAtualizacao;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	private Integer prioridade;
	private Integer status;
	private String titulo;
	private String observacoes;
	
	public Chamado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chamado(Integer id, Integer prioridade, Integer status, String titulo, String observacoes, Tecnico tecnico,
			Cliente cliente) {
		super();
		this.id = id;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.tecnico = tecnico;
		this.cliente = cliente;
	}
	
	public Chamado(ChamadoDTO chamado) 
	{
		super();
		this.id = chamado.getId();
		this.status = chamado.getStatusId();
		this.prioridade = chamado.getPrioridadeId();		
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataAtualizacao = chamado.getDataAtualizacao();
		this.dataFechamento = chamado.getDataFechamento();
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

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
