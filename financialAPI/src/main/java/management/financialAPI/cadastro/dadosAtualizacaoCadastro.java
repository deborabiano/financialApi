package management.financialAPI.cadastro;

import jakarta.validation.constraints.NotNull;

public record dadosAtualizacaoCadastro(
        @NotNull
        Long id,

        String nome,
        String email,
        String telefone,
        dadosEndereco endereco
) {
        // Ensure that DadosEndereco class is defined elsewhere in your code
}
