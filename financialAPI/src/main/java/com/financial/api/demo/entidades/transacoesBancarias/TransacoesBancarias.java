package com.financial.api.demo.entidades.transacoesBancarias;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacoes_bancarias")
public class TransacoesBancarias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contaId;

    private Integer valor; // Valor original como inteiro (centavos)

    private LocalDateTime dataTransacao;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacaoBancaria tipoTransacaoBancaria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDespesaBancaria tipoDespesaBancaria;

    private String moeda;

    private BigDecimal valorConvertido; // Valor convertido em BigDecimal

    // Metodo adicional (se necessário)
    public void deleteTransacaoById(Long id) {
    }
}
