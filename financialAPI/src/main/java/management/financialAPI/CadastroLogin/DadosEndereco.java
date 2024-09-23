package management.financialAPI.CadastroLogin;

public record DadosEndereco(
        String logradouro,
        String numero,
        String bairro,
        String cep,
        String cidade,
        String complemento,
        String uf
) {
}
