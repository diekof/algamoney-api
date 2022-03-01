package com.algaworks.algamoney.algamoneyapi.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.algaworks.algamoney.algamoneyapi.event.RecursoCriadoEvent;
import com.algaworks.algamoney.algamoneyapi.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.algaworks.algamoney.algamoneyapi.model.Lancamento;
import com.algaworks.algamoney.algamoneyapi.repository.LancamentoRepository;
import com.algaworks.algamoney.algamoneyapi.repository.filter.LancamentoFilter;
import com.algaworks.algamoney.algamoneyapi.service.LancamentoService;
import com.algaworks.algamoney.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;    

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private MessageSource messageSoucer;

    @GetMapping
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {

        Optional<Lancamento> optLancamento = lancamentoRepository.findById(codigo);

        return optLancamento.isPresent() ? ResponseEntity.ok(optLancamento.get()) : ResponseEntity.notFound().build();

    }

    @GetMapping("/pessoa/{codigoPessoa}")
    public ResponseEntity<List<Lancamento>> buscarPeloCodigoPessoa(@PathVariable Long codigoPessoa) {

        List<Lancamento> listLancamentos = lancamentoRepository.findByPessoaCodigo(codigoPessoa);

        return ResponseEntity.ok(listLancamentos);

    }

    @GetMapping("/{nomePessoa}/nome")
    public ResponseEntity<List<Lancamento>> buscarPeloCodigoPessoa(@PathVariable String nomePessoa) {

        List<Lancamento> listLancamentos = lancamentoRepository.findByPessoaNome(nomePessoa);

        return ResponseEntity.ok(listLancamentos);

    }

    @GetMapping("/listar")
    public ResponseEntity<List<Lancamento>> listarCustom(@RequestBody Lancamento lancamento, HttpServletResponse response) {
        List<Lancamento> listLancamentos =  lancamentoRepository.findByPessoaCodigoAndCategoriaCodigo(lancamento.getPessoa().getCodigo(), lancamento.getCategoria().getCodigo());
        return ResponseEntity.ok(listLancamentos);
    }

    @PostMapping()
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento lancamentoSalva = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo){
        lancamentoRepository.deleteById(codigo);
    }

    @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
        String mensagemUsuario = messageSoucer.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();

        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

        return ResponseEntity.badRequest().body(erros);
    }


}
