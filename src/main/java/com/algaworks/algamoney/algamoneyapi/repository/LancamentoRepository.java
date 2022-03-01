package com.algaworks.algamoney.algamoneyapi.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.algamoney.algamoneyapi.model.Lancamento;
import com.algaworks.algamoney.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

    public List<Lancamento> findByPessoaCodigo(Long codigo);
    public List<Lancamento> findByPessoaNome(String nome);
    public List<Lancamento> findByPessoaCodigoAndCategoriaCodigo(Long codigoPessoa, Long codigoCategoria);

}