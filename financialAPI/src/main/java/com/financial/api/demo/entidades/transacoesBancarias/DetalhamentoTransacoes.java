package com.financial.api.demo.entidades.transacoesBancarias;

public record DetalhamentoTransacoes(Long id, java.time.LocalDateTime data, Integer valor, TipoTransacaoBancaria tipo, Integer moeda, Integer valor_convertido ) {

    public DetalhamentoTransacoes(TransacoesBancarias transacoesBancarias){
        this(transacoesBancarias.getId(), transacoesBancarias.getDataTransacao(), transacoesBancarias.getValor(), transacoesBancarias.getTipoTransacaoBancaria(), transacoesBancarias.getValor(), transacoesBancarias.getValor());
    }


}
