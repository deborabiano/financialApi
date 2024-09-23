package management.financialAPI.CadastroLogin;

public record DadosCadastro(
        String nome,
        String cpf,
        String email,
        String dataNascimento,
        String telefone,
        DadosEndereco endereco
) {
        // Nenhuma alteração adicional necessária, já que o record já cria os métodos necessários automaticamente
}
