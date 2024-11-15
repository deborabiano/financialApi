package management.financialAPI.CadastroLogin;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCadastro(
       @NotNull
        Long id,

        String nome,
        String email,
        String telefone,
        DadosEndereco endereco
) {

}
