package com.udemy.helpdesk.domain;

import com.udemy.helpdesk.domain.enums.Perfil;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Pessoa
{
	private static final long serialVersionUID = 1L;
	
	public Admin() {
		super();
		addPerfil(Perfil.ADMIN);
	}

	public Admin(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.ADMIN);
	}
}
