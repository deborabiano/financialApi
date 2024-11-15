package management.financialAPI.CadastroLogin;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    // Inclusão do campo de senha
    @NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "A senha deve conter pelo menos 8 caracteres, incluindo letras, números e pelo menos um caractere especial.")
    private String senha;

    public Cadastro(DadosCadastro dadosCadastro) {
        this.ativo = true;
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.dataNascimento = dadosCadastro.dataNascimento();
        this.email = dadosCadastro.email();
        this.telefone = dadosCadastro.telefone();
        this.endereco = new Endereco(dadosCadastro.endereco());
        this.senha = encryptPassword(dadosCadastro.senha()); // Criptografa a senha ao criar
    }

    // Método para criptografar a senha
    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
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
