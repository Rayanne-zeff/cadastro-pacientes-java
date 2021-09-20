--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4 (Ubuntu 13.4-1.pgdg20.04+1)
-- Dumped by pg_dump version 13.4 (Ubuntu 13.4-1.pgdg20.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: cadastro_pacientes; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE SCHEMA cadastro_pacientes;


ALTER SCHEMA cadastro_pacientes OWNER TO postgres;

\c cadastro_pacientes

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: enfermeiro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS cadastro_pacientes.enfermeiro (
                                                             pessoa_id bigint NOT NULL,
                                                             enfermeiro_cre character varying(255),
    enfermeiro_data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    enfermeiro_criacao timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
    );


ALTER TABLE cadastro_pacientes.enfermeiro OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cadastro_pacientes.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro_pacientes.hibernate_sequence OWNER TO postgres;

--
-- Name: medico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS cadastro_pacientes.medico (
                                                         pessoa_id bigint NOT NULL,
                                                         medico_crm character varying(255),
    medico_data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    medico_data_criacao timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
    );


ALTER TABLE cadastro_pacientes.medico OWNER TO postgres;

--
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS cadastro_pacientes.paciente (
                                                           pessoa_id bigint NOT NULL,
                                                           paciente_altura double precision,
                                                           paciente_data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                                           paciente_data_criacao timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                           paciente_data_nascimento date,
                                                           paciente_peso double precision,
                                                           paciente_uf character varying(255) NOT NULL
    );


ALTER TABLE cadastro_pacientes.paciente OWNER TO postgres;

--
-- Name: perfil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS cadastro_pacientes.perfil (
    perfil_name character varying(255) NOT NULL
    );


ALTER TABLE cadastro_pacientes.perfil OWNER TO postgres;

--
-- Name: pessoa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS cadastro_pacientes.pessoa (
                                                         pessoa_id bigint NOT NULL,
                                                         pessoa_cpf character varying(255) NOT NULL,
    pessoa_data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    pessoa_data_criacao timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    pessoa_name character varying(255) NOT NULL,
    pessoa_tipo character varying(10) NOT NULL
    );


ALTER TABLE cadastro_pacientes.pessoa OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS cadastro_pacientes.usuario (
                                                          usuario_id bigint NOT NULL,
                                                          usuario_data_alteracao timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                                          usuario_data_criacao timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                          usuario_login character varying(20) NOT NULL,
    usuario_senha character varying(255) NOT NULL,
    pessoa_id bigint NOT NULL
    );


ALTER TABLE cadastro_pacientes.usuario OWNER TO postgres;

--
-- Name: usuario_perfil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS cadastro_pacientes.usuario_perfil (
                                                                 usuario_perfil bigint NOT NULL,
                                                                 perfil_name character varying(255) NOT NULL
    );


ALTER TABLE cadastro_pacientes.usuario_perfil OWNER TO postgres;

--
-- Data for Name: enfermeiro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cadastro_pacientes.enfermeiro (pessoa_id, enfermeiro_cre, enfermeiro_data_alteracao, enfermeiro_criacao) VALUES (1, '316554', null, '2021-09-20 03:46:55.369519');


--
-- Data for Name: medico; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cadastro_pacientes.medico (pessoa_id, medico_crm, medico_data_alteracao, medico_data_criacao) VALUES (2, '23132', null, '2021-09-20 03:46:26.447913');
INSERT INTO cadastro_pacientes.medico (pessoa_id, medico_crm, medico_data_alteracao, medico_data_criacao) VALUES (5, '78786', null, '2021-09-20 03:46:26.447913');


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cadastro_pacientes.paciente (pessoa_id, paciente_altura, paciente_data_alteracao, paciente_data_criacao, paciente_data_nascimento, paciente_peso, paciente_uf) VALUES (3, 1.86, null, '2021-09-20 03:45:42.378931', '1948-08-20', 100, 'SP');
INSERT INTO cadastro_pacientes.paciente (pessoa_id, paciente_altura, paciente_data_alteracao, paciente_data_criacao, paciente_data_nascimento, paciente_peso, paciente_uf) VALUES (4, 1.85, null, '2021-09-20 03:45:42.378931', '1963-08-03', 115, 'AM');


--
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cadastro_pacientes.perfil (perfil_name) VALUES ('ROLE_MEDICO');
INSERT INTO cadastro_pacientes.perfil (perfil_name) VALUES ('ROLE_ENFERMEIRO');
INSERT INTO cadastro_pacientes.perfil (perfil_name) VALUES ('ROLE_PACIENTE');


--
-- Data for Name: pessoa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cadastro_pacientes.pessoa (pessoa_id, pessoa_cpf, pessoa_data_alteracao, pessoa_data_criacao, pessoa_name, pessoa_tipo) VALUES (1, 'NTUyLjA1NS42MjAtMjE=', null, '2021-09-20 03:40:45.037569', 'Freddie Mercury', 'Enfermeiro');
INSERT INTO cadastro_pacientes.pessoa (pessoa_id, pessoa_cpf, pessoa_data_alteracao, pessoa_data_criacao, pessoa_name, pessoa_tipo) VALUES (2, 'NDc4Ljk2NC4yNzAtNDQ=', null, '2021-09-20 03:43:14.289506', 'Brian Johnson', 'Medico');
INSERT INTO cadastro_pacientes.pessoa (pessoa_id, pessoa_cpf, pessoa_data_alteracao, pessoa_data_criacao, pessoa_name, pessoa_tipo) VALUES (3, 'MjA3Ljk0My41NTAtNzI=', null, '2021-09-20 03:43:14.289506', 'Robert Plant', 'Paciente');
INSERT INTO cadastro_pacientes.pessoa (pessoa_id, pessoa_cpf, pessoa_data_alteracao, pessoa_data_criacao, pessoa_name, pessoa_tipo) VALUES (4, 'NTM0LjY1Ni4xOTAtMDU=', null, '2021-09-20 03:43:14.289506', 'James Hetfield', 'Paciente');
INSERT INTO cadastro_pacientes.pessoa (pessoa_id, pessoa_cpf, pessoa_data_alteracao, pessoa_data_criacao, pessoa_name, pessoa_tipo) VALUES (5, 'ODQ0LjM1Ny4zMjAtMTc=', null, '2021-09-20 03:43:14.289506', 'Elvis Presley', 'Medico');


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cadastro_pacientes.usuario (usuario_id, usuario_data_alteracao, usuario_data_criacao, usuario_login, usuario_senha, pessoa_id) VALUES (1, null, '2021-09-20 03:48:54.252399', 'freddie', '$2a$10$LMbXGM5IvXTao3ksuIb6D.OjrLmkDBkfFQgoyqZoitcKCjwUVSpWW', 1);
INSERT INTO cadastro_pacientes.usuario (usuario_id, usuario_data_alteracao, usuario_data_criacao, usuario_login, usuario_senha, pessoa_id) VALUES (2, null, '2021-09-20 03:48:54.252399', 'brian', '$2a$10$LMbXGM5IvXTao3ksuIb6D.OjrLmkDBkfFQgoyqZoitcKCjwUVSpWW', 2);
INSERT INTO cadastro_pacientes.usuario (usuario_id, usuario_data_alteracao, usuario_data_criacao, usuario_login, usuario_senha, pessoa_id) VALUES (3, null, '2021-09-20 03:48:54.252399', 'robert', '$2a$10$LMbXGM5IvXTao3ksuIb6D.OjrLmkDBkfFQgoyqZoitcKCjwUVSpWW', 3);
INSERT INTO cadastro_pacientes.usuario (usuario_id, usuario_data_alteracao, usuario_data_criacao, usuario_login, usuario_senha, pessoa_id) VALUES (4, null, '2021-09-20 03:48:54.252399', 'james', '$2a$10$LMbXGM5IvXTao3ksuIb6D.OjrLmkDBkfFQgoyqZoitcKCjwUVSpWW', 4);
INSERT INTO cadastro_pacientes.usuario (usuario_id, usuario_data_alteracao, usuario_data_criacao, usuario_login, usuario_senha, pessoa_id) VALUES (5, null, '2021-09-20 03:48:54.252399', 'elvis', '$2a$10$LMbXGM5IvXTao3ksuIb6D.OjrLmkDBkfFQgoyqZoitcKCjwUVSpWW', 5);


--
-- Data for Name: usuario_perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cadastro_pacientes.usuario_perfil (usuario_perfil, perfil_name) VALUES (1, 'ROLE_ENFERMEIRO');
INSERT INTO cadastro_pacientes.usuario_perfil (usuario_perfil, perfil_name) VALUES (2, 'ROLE_MEDICO');
INSERT INTO cadastro_pacientes.usuario_perfil (usuario_perfil, perfil_name) VALUES (3, 'ROLE_PACIENTE');
INSERT INTO cadastro_pacientes.usuario_perfil (usuario_perfil, perfil_name) VALUES (4, 'ROLE_PACIENTE');
INSERT INTO cadastro_pacientes.usuario_perfil (usuario_perfil, perfil_name) VALUES (5, 'ROLE_MEDICO');


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cadastro_pacientes.hibernate_sequence', 1, false);


--
-- Name: enfermeiro enfermeiro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.enfermeiro
    ADD CONSTRAINT enfermeiro_pkey PRIMARY KEY (pessoa_id);


--
-- Name: medico medico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (pessoa_id);


--
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (pessoa_id);


--
-- Name: perfil perfil_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (perfil_name);


--
-- Name: pessoa pessoa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (pessoa_id);


--
-- Name: usuario uk_5e7j7wtnccvkfespjogdrbvi0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.usuario
    ADD CONSTRAINT uk_5e7j7wtnccvkfespjogdrbvi0 UNIQUE (usuario_login);


--
-- Name: pessoa uk_tbmue97julbq73jgja3sitrca; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.pessoa
    ADD CONSTRAINT uk_tbmue97julbq73jgja3sitrca UNIQUE (pessoa_cpf);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usuario_id);


--
-- Name: medico FK2wcy4s7ub2caeiye8jmldjxqi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.medico
    ADD CONSTRAINT "FK2wcy4s7ub2caeiye8jmldjxqi" FOREIGN KEY (pessoa_id) REFERENCES cadastro_pacientes.pessoa(pessoa_id);


--
-- Name: usuario_perfil FK64x8utif4jyq1d50vvhe7hxy0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.usuario_perfil
    ADD CONSTRAINT "FK64x8utif4jyq1d50vvhe7hxy0" FOREIGN KEY (perfil_name) REFERENCES cadastro_pacientes.perfil(perfil_name);


--
-- Name: usuario_perfil FK7jp1ec1aar6a3xkvpgkr3th2k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.usuario_perfil
    ADD CONSTRAINT "FK7jp1ec1aar6a3xkvpgkr3th2k" FOREIGN KEY (usuario_perfil) REFERENCES cadastro_pacientes.usuario(usuario_id);


--
-- Name: usuario FKe2eoii2pb71nrqh59x7lqujjr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.usuario
    ADD CONSTRAINT "FKe2eoii2pb71nrqh59x7lqujjr" FOREIGN KEY (pessoa_id) REFERENCES cadastro_pacientes.pessoa(pessoa_id);


--
-- Name: paciente FKeb8td6hwygfwr92gj8g9wywtv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.paciente
    ADD CONSTRAINT "FKeb8td6hwygfwr92gj8g9wywtv" FOREIGN KEY (pessoa_id) REFERENCES cadastro_pacientes.pessoa(pessoa_id);


--
-- Name: enfermeiro FKhab0tn3nhokt01eqdq0uh58v9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cadastro_pacientes.enfermeiro
    ADD CONSTRAINT "FKhab0tn3nhokt01eqdq0uh58v9" FOREIGN KEY (pessoa_id) REFERENCES cadastro_pacientes.pessoa(pessoa_id);


--
-- PostgreSQL database dump complete
--

