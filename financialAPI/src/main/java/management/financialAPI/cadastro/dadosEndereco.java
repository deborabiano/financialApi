package management.financialAPI.cadastro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import management.financialAPI.user.enderecoUsuario;

public record dadosEndereco(
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
    public dadosEndereco(enderecoUsuario enderecoUsuario) {
        this(
                enderecoUsuario.getLogradouro(),
                enderecoUsuario.getNumero(),
                enderecoUsuario.getBairro(),
                enderecoUsuario.getCep(),
                enderecoUsuario.getCidade(),
                enderecoUsuario.getComplemento(),
                enderecoUsuario.getUf());
    }

    // Ensure that there is a definition for the Endereco class
}
