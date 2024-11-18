package com.financial.api.demo.service;

import com.financial.api.demo.entidades.transacoesBancarias.*;
import com.financial.api.demo.repository.TransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.PostConstruct;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class TransacoesService {

    @Autowired
    private TransacoesRepository transacaoRepository;

    @Value("${exchangerates.api.url}")
    private String apiUrl;

    @Value("${exchangerates.api.key}")
    private String apiKey;

    @Value("${exchangerates.api.moedaBase}")
    private String moedaBase;

    @Transactional
    public TransacoesBancarias cadastrarTransacao(TransacoesBancarias transacao) {
        TiposTaxas tiposTaxas = getExchangeRates2(transacao.getMoeda());
        transacao.setValorConvertido(tiposTaxas.getTaxaBase()); // Ajustar o valor convertido usando a taxa obtida

        return transacaoRepository.save(transacao); // Salvando a transação no repositório
    }

    public Page<ListagemTransacoes> listarTransacoes(Pageable paginacao) {
        return transacaoRepository.findAll(paginacao).map(ListagemTransacoes::new);
    }

    @Transactional
    public TransacoesBancarias atualizarTransacao(Long id, AtualizarTransacoes dados) {
        var transacao = transacaoRepository.getReferenceById(dados.id());
        // Atualizar a transação aqui
        return transacao;
    }

    @Transactional
    public void excluirTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    @PostConstruct
    public void init() {
        System.out.println("API URL: " + apiUrl);
        System.out.println("API Key: " + apiKey);
        System.out.println("Moeda Base: " + moedaBase);
    }

    // Metodo que faz a chamada à API de câmbio
    public ResponseEntity<BigDecimal> getExchangeRates(String baseCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/latest?access_key=" + apiKey + "&base=" + baseCurrency;

        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);

        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            Map<String, Object> responseBody = responseEntity.getBody();
            Map<String, Object> rates = (Map<String, Object>) responseBody.get("rates");
            BigDecimal rate = new BigDecimal(rates.get(moedaBase).toString());

            if (rate != null) {
                return ResponseEntity.ok(rate);
            } else {
                return ResponseEntity.badRequest().body(rate);
            }
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).build();
        }
    }

    public TiposTaxas getExchangeRates2(String baseCurrency) {
        WebClient webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .build();

        ResponseEntity<Map> responseEntity = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("access_key", apiKey)
                        .queryParam("base", baseCurrency)
                        .build())
                .retrieve()
                .toEntity(Map.class)
                .block(); // Aguardar a resposta de forma síncrona

        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            Map<String, Object> responseBody = responseEntity.getBody();
            Map<String, Object> rates = (Map<String, Object>) responseBody.get("rates");

            BigDecimal rate = new BigDecimal(rates.get(baseCurrency).toString());
            BigDecimal rateBRL = new BigDecimal(rates.get("BRL").toString());

            return new TiposTaxas(rate, rateBRL);
        }

        return new TiposTaxas(BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
