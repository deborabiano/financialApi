package management.financialAPI.DAO;

import management.financialAPI.Transacoes.TransacaoPix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoPixRepository extends JpaRepository<TransacaoPix, Long> {
    // adicionar métodos
}
