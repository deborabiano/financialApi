package management.financialAPI.CadastroLogin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuarios")
public class Usuario {

    // Getters e Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Cadastro cadastro;

    // Construtor padrão
    public Usuario() {
    }

    public Usuario(Long id, String cpf, String email, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

}
