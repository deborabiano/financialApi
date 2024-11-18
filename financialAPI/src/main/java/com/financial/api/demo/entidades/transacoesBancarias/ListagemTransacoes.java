package com.financial.api.demo.entidades.transacoesBancarias;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ListagemTransacoes(
        Long id,
        LocalDate data,
        BigDecimal valor,
        TipoTransacaoBancaria tipo,
        String moeda,
        BigDecimal valorConvertido // Corrigido o nome do atributo
) {
    public ListagemTransacoes(TransacoesBancarias transacoesBancarias) {
        this(
                transacoesBancarias.getId(),
                transacoesBancarias.getDataTransacao().toLocalDate(), // Convertendo para LocalDate
                BigDecimal.valueOf(transacoesBancarias.getValor()), // Convertendo Integer para BigDecimal
                transacoesBancarias.getTipoTransacaoBancaria(),
                transacoesBancarias.getMoeda(),
                transacoesBancarias.getValorConvertido()
        );
    }
}
