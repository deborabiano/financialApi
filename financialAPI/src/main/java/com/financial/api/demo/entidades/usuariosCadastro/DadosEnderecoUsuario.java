package com.financial.api.demo.entidades.usuariosCadastro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DadosEnderecoUsuario {

    @NotBlank
    private String logradouro;

    private String numero;

    @NotBlank
    private String bairro;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;

    @NotBlank
    private String cidade;

    private String complemento;

    @NotBlank
    private String uf;

    // Construtor principal com todos os atributos
    public DadosEnderecoUsuario(
            @NotBlank String logradouro,
            String numero,
            @NotBlank String bairro,
            @NotBlank @Pattern(regexp = "\\d{8}") String cep,
            @NotBlank String cidade,
            String complemento,
            @NotBlank String uf) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;
        this.uf = uf;
    }

    // Construtor copiando outro objeto DadosEnderecoUsuario
    public DadosEnderecoUsuario(DadosEnderecoUsuario endereco) {
        this(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getComplemento(),
                endereco.getUf()
        );
    }

    // Construtor para conversão de EnderecoUsuario
    public DadosEnderecoUsuario(EnderecoUsuario enderecoUsuario) {
        this(
                enderecoUsuario.getLogradouro(),
                enderecoUsuario.getNumero(),
                enderecoUsuario.getBairro(),
                enderecoUsuario.getCep(),
                enderecoUsuario.getCidade(),
                enderecoUsuario.getComplemento(),
                enderecoUsuario.getUf()
        );
    }
}
