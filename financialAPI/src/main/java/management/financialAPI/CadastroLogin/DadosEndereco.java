package management.financialAPI.CadastroLogin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,

        String numero,

        @NotBlank
        String bairro,

        @NotBlank
                @Pattern(regexp = "\\d{8}")
        String cep,

        @NotBlank
        String cidade,

        String complemento,

        @NotBlank
        String uf
) {
}
