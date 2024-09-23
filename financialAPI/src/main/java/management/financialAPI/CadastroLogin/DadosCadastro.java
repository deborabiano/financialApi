package management.financialAPI.CadastroLogin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastro(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String dataNascimento,

        @NotBlank
        String telefone,

        DadosEndereco endereco
) {

}
