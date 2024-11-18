package management.financialAPI.cadastro;

import management.financialAPI.user.cadastroUsuario;

public record dadosListagemUsuarios(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone
) {
    public dadosListagemUsuarios(cadastroUsuario cadastroUsuario) {
        this(
                cadastroUsuario.getId(),
                cadastroUsuario.getNome(),
                cadastroUsuario.getCpf(),
                cadastroUsuario.getEmail(),
                cadastroUsuario.getTelefone()
        );

    }
}
