package management.financialAPI.user;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import management.financialAPI.cadastro.dadosEndereco;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class enderecoUsuario {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String complemento;
    private String cidade;
    private String uf;

    // Construtor que inicializa os campos a partir de DadosEndereco
    public enderecoUsuario(dadosEndereco dadosEndereco) {
        this.logradouro = dadosEndereco.logradouro();
        this.numero = dadosEndereco.numero();
        this.bairro = dadosEndereco.bairro();
        this.cep = dadosEndereco.cep();
        this.complemento = dadosEndereco.complemento();
        this.cidade = dadosEndereco.cidade();
        this.uf = dadosEndereco.uf();
    }

    public void atualizarDados(dadosEndereco endereco) {

        if (endereco.logradouro() != null) {
            this.logradouro = endereco.logradouro();
        }
        if (endereco.numero() != null) {
            this.numero = endereco.numero();
        }
        if (endereco.bairro() != null) {
            this.bairro = endereco.bairro();
        }
        if (endereco.cep() != null) {
            this.cep = endereco.cep();
        }
        if (endereco.complemento() != null) {
            this.complemento = endereco.complemento();
        }
        if (endereco.cidade() != null) {
            this.cidade = endereco.cidade();
        }
        if (endereco.uf() != null) {
            this.uf = endereco.uf();
        }
    }

}