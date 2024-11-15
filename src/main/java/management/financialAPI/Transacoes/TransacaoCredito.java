package management.financialAPI.Transacoes;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes_credito")
public class TransacaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contaId;  // ID da conta onde a transação foi realizada

    private BigDecimal valor;  // Valor da transação de crédito

    private LocalDateTime dataTransacao;  // Data e hora da transação

    private String descricao;  // Descrição da transação

    public TransacaoCredito() {}

    public TransacaoCredito(Long contaId, BigDecimal valor, LocalDateTime dataTransacao, String descricao) {
        this.contaId = contaId;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.descricao = descricao;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
