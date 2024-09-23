package management.financialAPI.CadastroLogin;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cadastro")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Cadastro(DadosCadastro dadosCadastro) {
        this.ativo = true;
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.dataNascimento = dadosCadastro.dataNascimento();
        this.email = dadosCadastro.email();
        this.telefone = dadosCadastro.telefone();
        this.endereco = new Endereco(dadosCadastro.endereco());
    }


    public void atualizar(DadosCadastro dadosCadastro) {
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.dataNascimento = dadosCadastro.dataNascimento();
        this.email = dadosCadastro.email();
        this.telefone = dadosCadastro.telefone();
        this.endereco = new Endereco(dadosCadastro.endereco());
    }

    public void atualizarDados(@Valid DadosAtualizacaoCadastro dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarDados(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}