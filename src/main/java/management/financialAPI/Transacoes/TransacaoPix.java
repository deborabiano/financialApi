package management.financialAPI.Transacoes;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes_pix")
public class TransacaoPix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contaId;  // ID da conta onde a transação foi realizada

    private BigDecimal valor;  // Valor da transação via PIX

    private LocalDateTime dataTransacao;  // Data e hora da transação

    private String chavePix;  // Chave PIX do destinatário

    private String descricao;  // Descrição da transação

    public TransacaoPix() {}

    public TransacaoPix(Long contaId, BigDecimal valor, LocalDateTime dataTransacao, String chavePix, String descricao) {
        this.contaId = contaId;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.chavePix = chavePix;
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

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
