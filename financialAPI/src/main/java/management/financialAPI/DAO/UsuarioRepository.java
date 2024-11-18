package management.financialAPI.DAO;

import management.financialAPI.CadastroLogin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //UserDetails findByLogin(Class<? extends TokenService> email);

    //UserDetails findByLogin(String username);
}
