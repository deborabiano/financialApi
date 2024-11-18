package management.financialAPI.repository;

import management.financialAPI.user.usuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface usuarioRepository extends JpaRepository<usuarioLogin, Long> {
    UserDetails findByLogin(String login);
}
