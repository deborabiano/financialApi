package management.financialAPI.CadastroLogin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

        //Validações de Cadastro
public record DadosCadastro(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos.")
        String cpf,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        String email,

        @NotBlank(message = "Data de Nascimento é obrigatória")
        String dataNascimento,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "Senha é obrigatória")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$",
                message = "A senha deve conter pelo menos 8 caracteres, incluindo letras, números e pelo menos um caractere especial.")
        String senha,

        DadosEndereco endereco
) {
        public DadosCadastro(Cadastro consulta) {
                this(
                        consulta.getNome(),
                        consulta.getCpf(),
                        consulta.getEmail(),
                        consulta.getDataNascimento(),
                        consulta.getTelefone(),
                        consulta.getSenha(),
                        new DadosEndereco(consulta.getEndereco())
                );
        }
}
