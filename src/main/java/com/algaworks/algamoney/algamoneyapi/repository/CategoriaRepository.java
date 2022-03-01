package com.algaworks.algamoney.algamoneyapi.repository;

import com.algaworks.algamoney.algamoneyapi.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
