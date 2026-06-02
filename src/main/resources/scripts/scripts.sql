CREATE TABLE chamados (
	id int4 NOT NULL,
	data_abertura timestamptz NOT NULL,
	data_fechamento timestamptz NULL,
	tecnico_id int4 NULL,
	cliente_id int4 NULL,
	titulo varchar(255) NULL,
	observacoes varchar(255) NULL,
	status int4 NULL,
	prioridade int4 NULL,
	data_atualizacao timestamptz NULL,
	CONSTRAINT chamado_pk PRIMARY KEY (id),
	CONSTRAINT fk8bjv37p24o05tq03f5t4h3hgl FOREIGN KEY (tecnico_id) REFERENCES pessoas(id),
	CONSTRAINT fkbco278opsmt208i3soj8gdcuf FOREIGN KEY (cliente_id) REFERENCES pessoas(id)
);

CREATE TABLE pessoas (
	id int4 NOT NULL,
	cpf varchar(255) NULL,
	email varchar(255) NULL,
	perfils_id int4 NULL,
	data_criacao timestamptz NULL,
	nome varchar(255) NULL,
	senha varchar(255) NULL,
	perfil varchar(255) NULL,
	data_atualizacao timestamptz NULL,
	CONSTRAINT cpf_uk UNIQUE (cpf),
	CONSTRAINT mail_uk UNIQUE (email),
	CONSTRAINT pessoas_pk PRIMARY KEY (id),
	CONSTRAINT fk_perfil FOREIGN KEY (perfils_id) REFERENCES perfis(codigo)
);

CREATE TABLE perfis (
	codigo int4 NOT NULL,
	descricao varchar NULL,
	CONSTRAINT perfis_pk PRIMARY KEY (codigo)
);

CREATE SEQUENCE chamados_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
CREATE SEQUENCE pessoas_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
insert into perfis values(0, 'ROLE_ADMIN');
insert into perfis values(1, 'ROLE_CLIENTE');
insert into perfis values(2, 'ROLE_TECNICO');
insert into pessoas values(nextval('pessoas_seq'), '97694186259', 'admin@mail.com', 0, now(), 'admin', '$2a$10$5msU.GNbERAvFJtwBKOuM.8Vzq4YYBDYQbHK6gcoRHtMypRL9OfSS', 'admin', now());