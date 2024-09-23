package management.financialAPI.DAO;

import management.financialAPI.CadastroLogin.Cadastro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    default void deleteById(Long id) {

    }

    Page<Cadastro> findAllByAtivoTrue(Pageable paginacao);
}
