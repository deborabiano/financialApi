package management.financialAPI.repository;

import management.financialAPI.user.cadastroUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface cadastroUserRepository extends JpaRepository<cadastroUsuario, Long> {

    // Encontrar todos os cadastros ativos
    Page<cadastroUsuario> findAllByAtivoTrue(Pageable paginacao);

    // Verificar se um CPF já está cadastrado
    boolean existsByCpf(String cpf);

    Optional<cadastroUsuario> findByCpfOrEmail(String cpf, String email);
}
