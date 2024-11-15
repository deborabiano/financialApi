package management.financialAPI.CadastroLogin;

public record DadosListagemUsuarios(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone
) {
    public DadosListagemUsuarios(Cadastro cadastro) {
        this(
                cadastro.getId(),
                cadastro.getNome(),
                cadastro.getCpf(),
                cadastro.getEmail(),
                cadastro.getTelefone()
        );

    }
}
