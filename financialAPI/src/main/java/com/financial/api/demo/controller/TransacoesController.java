package com.financial.api.demo.controller;

import com.financial.api.demo.entidades.transacoesBancarias.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.financial.api.demo.service.TransacoesService;

import java.math.BigDecimal;

@RestController
@RequestMapping("transacoes")
//@SecurityRequirement(name = "bearer-key")
public class TransacoesController {

    @Autowired
    private TransacoesService transacoesService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoTransacoes> cadastroTransacao(@RequestBody @Valid TransacoesBancarias transacao, UriComponentsBuilder uriBuilder) {
        // Salvando a transação
        TransacoesBancarias transacaoSalva = transacoesService.cadastrarTransacao(transacao);

        // Gerando o URI para a nova transação
        var uri = uriBuilder.path("/transacoes/{id}").buildAndExpand(transacaoSalva.getId()).toUri();

        // Retornando a resposta com o detalhamento da transação
        return ResponseEntity.created(uri).body(new DetalhamentoTransacoes(transacaoSalva));
    }


    @GetMapping
    public ResponseEntity<Page<ListagemTransacoes>> listar(Pageable paginacao) {
        var page = transacoesService.listarTransacoes(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhamentoTransacoes> atualizar(@PathVariable Long id, @RequestBody AtualizarTransacoes dados) {
        var transacaoAtualizada = transacoesService.atualizarTransacao(id, dados);
        return ResponseEntity.ok(new DetalhamentoTransacoes(transacaoAtualizada));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirTransacao(@PathVariable Long id) {
        transacoesService.excluirTransacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cotacao")
    public ResponseEntity<BigDecimal> getExchangeRates(@RequestBody BaseMoeda baseMoeda) {
        String moeda = baseMoeda.getBaseCurrency();
        return transacoesService.getExchangeRates(moeda);
    }

    @GetMapping("/cotacoes")
    public ResponseEntity<TiposTaxas> buscaCotacoes(@RequestBody BaseMoeda baseMoeda) {
        String moeda = baseMoeda.getBaseCurrency();
        TiposTaxas ratesresult = transacoesService.getExchangeRates2(moeda);
        return ResponseEntity.ok(ratesresult);
    }
}
