package com.algaworks.algamoney.algamoneyapi.repository.lancamento;

import com.algaworks.algamoney.algamoneyapi.model.Lancamento;
import com.algaworks.algamoney.algamoneyapi.repository.filter.LancamentoFilter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
