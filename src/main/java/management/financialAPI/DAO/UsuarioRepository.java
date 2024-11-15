package management.financialAPI.DAO;

import management.financialAPI.CadastroLogin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca por email ou cpf
    Optional<Usuario> findByEmailOrCpf(String email, String cpf);

    // Busca usuários com cadastro associado
    @Query("FROM Usuario u WHERE u.cpf IS NOT NULL")
    List<Usuario> buscaUsuario(String email, String cpf);
}
