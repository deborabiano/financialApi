package com.financial.api.demo.entidades.transacoesBancarias;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizarTransacoes(
        @NotNull
        Long id,

        BigDecimal valor,

        TipoTransacaoBancaria tipo) {

}