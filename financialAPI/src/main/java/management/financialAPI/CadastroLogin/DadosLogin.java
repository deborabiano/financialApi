package management.financialAPI.CadastroLogin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DadosLogin {

    @NotBlank(message = "Email ou CPF é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    // Construtor padrão
    public DadosLogin() {}

    // Construtor com parâmetros
    public DadosLogin(String email, String senha, String cpf) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    // Getter e Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter para senha
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Getter e Setter para CPF
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
