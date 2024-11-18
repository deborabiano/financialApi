package com.financial.api.demo.entidades.transacoesBancarias;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


public final class CadastroTransacoes {
    private final @NotNull LocalDate data;
    private final @NotNull BigDecimal valor;
    private final @NotNull TipoTransacaoBancaria tipo;
    private final @NotNull String moeda;

    public CadastroTransacoes(

            @NotNull
            LocalDate data,

            @NotNull
            BigDecimal valor,

            @NotNull
            TipoTransacaoBancaria tipo,

            @NotNull
            String moeda) {
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
        this.moeda = moeda;
    }

    public @NotNull LocalDate data() {
        return data;
    }

    public @NotNull BigDecimal valor() {
        return valor;
    }

    public @NotNull TipoTransacaoBancaria tipo() {
        return tipo;
    }

    public @NotNull String moeda() {
        return moeda;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CadastroTransacoes) obj;
        return Objects.equals(this.data, that.data) &&
                Objects.equals(this.valor, that.valor) &&
                Objects.equals(this.tipo, that.tipo) &&
                Objects.equals(this.moeda, that.moeda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, valor, tipo, moeda);
    }

    @Override
    public String toString() {
        return "CadastroTransacoes[" +
                "data=" + data + ", " +
                "valor=" + valor + ", " +
                "tipo=" + tipo + ", " +
                "moeda=" + moeda + ']';
    }


}
