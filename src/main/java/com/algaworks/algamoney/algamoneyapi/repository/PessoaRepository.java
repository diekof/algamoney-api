package com.algaworks.algamoney.algamoneyapi.repository;

import com.algaworks.algamoney.algamoneyapi.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
