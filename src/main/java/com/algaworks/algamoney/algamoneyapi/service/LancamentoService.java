package com.algaworks.algamoney.algamoneyapi.service;

import java.util.Optional;

import javax.validation.Valid;

import com.algaworks.algamoney.algamoneyapi.model.Lancamento;
import com.algaworks.algamoney.algamoneyapi.model.Pessoa;
import com.algaworks.algamoney.algamoneyapi.repository.LancamentoRepository;
import com.algaworks.algamoney.algamoneyapi.repository.PessoaRepository;
import com.algaworks.algamoney.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired 
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(@Valid Lancamento lancamento) {
        Optional<Pessoa> optPessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if (!optPessoa.isPresent() || optPessoa.get().isInativo()){
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
    
}
