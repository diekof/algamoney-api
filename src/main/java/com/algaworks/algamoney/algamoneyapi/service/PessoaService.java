package com.algaworks.algamoney.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algamoney.algamoneyapi.model.Lancamento;
import com.algaworks.algamoney.algamoneyapi.model.Pessoa;
import com.algaworks.algamoney.algamoneyapi.repository.LancamentoRepository;
import com.algaworks.algamoney.algamoneyapi.repository.PessoaRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa){

        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
        
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    public Pessoa buscarPessoaPeloCodigo(Long codigo) {
        Optional<Pessoa> optPessoa = pessoaRepository.findById(codigo);
        if (!optPessoa.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }

        return optPessoa.get();
    }

    public List<Lancamento> buscarLancamentoPelaPessoaCodigo(Long codigo) {
        List<Lancamento> listLancamentos = lancamentoRepository.findByPessoaCodigo(codigo);
        // if (!listLancamentos.isEmpty())
        //     throw new EmptyResultDataAccessException(1);

        return listLancamentos;
    }

    public void remover(Long codigo) {

        List<Lancamento>  lancamentoRemover = buscarLancamentoPelaPessoaCodigo(codigo);
        // if (!lancamentoRemover.isEmpty())
        pessoaRepository.deleteById(codigo);

    }

    
}
