package com.algaworks.algamoney.algamoneyapi.repository.lancamento;

import java.util.List;

import com.algaworks.algamoney.algamoneyapi.model.Lancamento;
import com.algaworks.algamoney.algamoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
