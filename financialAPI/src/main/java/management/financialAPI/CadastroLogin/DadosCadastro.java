package management.financialAPI.CadastroLogin;

public record DadosCadastro(
        String nome,
        String cpf,
        String dataNascimento,
        String email,
        String telefone,
        DadosEndereco dadosEndereco
) {

        }

