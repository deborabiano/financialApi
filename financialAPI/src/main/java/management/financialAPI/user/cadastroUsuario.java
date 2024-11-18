package management.financialAPI.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import management.financialAPI.cadastro.dadosAtualizacaoCadastro;
import management.financialAPI.cadastro.dadosCadastro;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Table(name = "cadastro")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class cadastroUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "Data de Nascimento é obrigatório")
    private String dataNascimento;

    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    private String telefone;

    @Embedded
    private management.financialAPI.user.enderecoUsuario enderecoUsuario;

    private boolean ativo;

    @NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$",
            message = "A senha deve conter pelo menos 8 caracteres, incluindo letras, números e pelo menos um caractere especial.")
    private String senha;

    public cadastroUsuario(dadosCadastro dadosCadastro) {
        this.ativo = true;
        this.nome = dadosCadastro.nome();
        this.cpf = dadosCadastro.cpf();
        this.dataNascimento = dadosCadastro.dataNascimento();
        this.email = dadosCadastro.email();
        this.telefone = dadosCadastro.telefone();
        this.enderecoUsuario = new enderecoUsuario(dadosCadastro.endereco());
        this.senha = encryptPassword(dadosCadastro.senha());
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public void atualizarDados(@Valid dadosAtualizacaoCadastro dados) {
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
            this.enderecoUsuario.atualizarDados(dados.endereco());
        }
    }
    public void excluir() {
        this.ativo = false;
    }
}
