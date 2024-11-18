package com.financial.api.demo.entidades.transacoesBancarias;

import java.time.LocalDate;

public record RelatorioData(
        LocalDate dataInicio,
        LocalDate dataFim) {
}
