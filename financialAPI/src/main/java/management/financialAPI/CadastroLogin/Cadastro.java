package management.financialAPI.CadastroLogin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cadastro") // Nome correto da tabela
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

    public Cadastro(DadosCadastro dadosCadastro) {
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.dataNascimento = dadosCadastro.dataNascimento();
        this.email = dadosCadastro.email();
        this.telefone = dadosCadastro.telefone();
        this.endereco = new Endereco(dadosCadastro.endereco()); // Certifique-se de que dadosCadastro.endereco() está correto
    }

    // Você pode adicionar um método para atualizar os dados, se necessário
    public void atualizar(DadosCadastro dadosCadastro) {
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.dataNascimento = dadosCadastro.dataNascimento();
        this.email = dadosCadastro.email();
        this.telefone = dadosCadastro.telefone();
        this.endereco = new Endereco(dadosCadastro.endereco());
    }
}