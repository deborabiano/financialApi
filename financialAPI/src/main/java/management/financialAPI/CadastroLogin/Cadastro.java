package management.financialAPI.CadastroLogin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "financialapi")
@Entity(name = "cadastro")
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

    // Construtor para inicializar os campos
    public Cadastro(DadosCadastro dadosCadastro) {
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.dataNascimento = dadosCadastro.dataNascimento();
        this.email = dadosCadastro.email();
        //this.endereco = new Endereco(dadosCadastro.endereco());
    }
}
