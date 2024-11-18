package com.financial.api.demo.entidades.transacoesBancarias;

import java.math.BigDecimal;

public record TiposTaxas(BigDecimal rate, BigDecimal rateBRL) {

    // Metodo para retornar a taxa base (taxa da moeda fornecida)
    public BigDecimal getTaxaBase() {
        return rate; // Retorna a taxa de câmbio para a moeda fornecida
    }
}
