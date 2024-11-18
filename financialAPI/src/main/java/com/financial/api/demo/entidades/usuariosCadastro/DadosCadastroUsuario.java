package com.financial.api.demo.entidades.usuariosCadastro;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Table(name = "cadastro")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DadosCadastroUsuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @NotBlank(message = "CPF é obrigatório")
        private String cpf;

        @NotBlank(message = "Data de Nascimento é obrigatório")
        private String dataNascimento;

        @NotBlank(message = "E-mail é obrigatório")
        private String email;

        private String telefone;

        @Embedded
        private EnderecoUsuario enderecoUsuario;

        private boolean ativo;

        @NotBlank(message = "A senha é obrigatória")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$",
                message = "A senha deve conter pelo menos 8 caracteres, incluindo letras, números e pelo menos um caractere especial.")
        private String senha;

        // Construtor personalizado que copia dados de outro objeto
        public DadosCadastroUsuario(String nome, String cpf, String dataNascimento, String email, String telefone, EnderecoUsuario enderecoUsuario, String senha) {
                this.nome = nome;
                this.cpf = cpf;
                this.dataNascimento = dataNascimento;
                this.email = email;
                this.telefone = telefone;
                this.enderecoUsuario = enderecoUsuario;
                this.senha = senha;
                this.ativo = true; // Usuário criado como ativo por padrão
        }

        // Setters
        public void setNome(String nome) {
                this.nome = nome;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        public void setDataNascimento(String dataNascimento) {
                this.dataNascimento = dataNascimento;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public void setTelefone(String telefone) {
                this.telefone = telefone;
        }

        public void setEnderecoUsuario(EnderecoUsuario enderecoUsuario) {
                this.enderecoUsuario = enderecoUsuario;
        }

        public void setAtivo(boolean ativo) {
                this.ativo = ativo;
        }

        public void setSenha(String senha) {
                this.senha = senha;
        }

        // Metodo para atualizar dados do usuário
        public void atualizarDados(@Valid AtualizarUsuario atualizarUsuario) {
                if (atualizarUsuario.nome() != null) {
                        this.nome = atualizarUsuario.nome();
                }
                if (atualizarUsuario.email() != null) {
                        this.email = atualizarUsuario.email();
                }
                if (atualizarUsuario.telefone() != null) {
                        this.telefone = atualizarUsuario.telefone();
                }
                if (atualizarUsuario.endereco() != null) {
                        if (this.enderecoUsuario == null) {
                                this.enderecoUsuario = new EnderecoUsuario();
                        } else {
                                this.enderecoUsuario.atualizarDados(atualizarUsuario.endereco());
                        }
                }
        }

        // Metodo para "excluir" o usuário, marcando-o como inativo
        public void excluir() {
                this.ativo = false;
        }
}
