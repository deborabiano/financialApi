package management.financialAPI.DAO;

import management.financialAPI.CadastroLogin.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
}
