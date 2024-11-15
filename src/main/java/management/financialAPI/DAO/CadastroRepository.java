package management.financialAPI.DAO;

import management.financialAPI.CadastroLogin.Cadastro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    // Encontrar todos os cadastros ativos
    Page<Cadastro> findAllByAtivoTrue(Pageable paginacao);

    // Verificar se um CPF já está cadastrado
    boolean existsByCpf(String cpf);
}
