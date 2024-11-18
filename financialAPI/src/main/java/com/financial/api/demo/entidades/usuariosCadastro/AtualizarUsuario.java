package com.financial.api.demo.entidades.usuariosCadastro;

import jakarta.validation.constraints.NotNull;

// Record para atualizar informações de um usuário existente
public record AtualizarUsuario(
        @NotNull(message = "O ID do usuário é obrigatório")
        Long id,

        String nome,
        String email,
        String telefone,
        DadosEnderecoUsuario endereco
) {
    // O método getEndereco é desnecessário, pois o record já possui os métodos automáticos
    // para acessar os campos (nome, email, telefone, endereco, etc.)
}
