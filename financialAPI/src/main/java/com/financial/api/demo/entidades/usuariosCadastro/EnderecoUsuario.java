package com.financial.api.demo.entidades.usuariosCadastro;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoUsuario {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String complemento;
    private String cidade;
    private String uf;

    // Construtor que inicializa os campos a partir de DadosEnderecoUsuario
    public EnderecoUsuario(DadosEnderecoUsuario dadosEndereco) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
    }

    // Metodo para atualizar dados do endereço
    public void atualizarDados(DadosEnderecoUsuario enderecoUsuario) {
        if (enderecoUsuario.getLogradouro() != null) {
            this.logradouro = enderecoUsuario.getLogradouro();
        }
        if (enderecoUsuario.getNumero() != null) {
            this.numero = enderecoUsuario.getNumero();
        }
        if (enderecoUsuario.getBairro() != null) {
            this.bairro = enderecoUsuario.getBairro();
        }
        if (enderecoUsuario.getCep() != null) {
            this.cep = enderecoUsuario.getCep();
        }
        if (enderecoUsuario.getComplemento() != null) {
            this.complemento = enderecoUsuario.getComplemento();
        }
        if (enderecoUsuario.getCidade() != null) {
            this.cidade = enderecoUsuario.getCidade();
        }
        if (enderecoUsuario.getUf() != null) {
            this.uf = enderecoUsuario.getUf();
        }
    }
}
