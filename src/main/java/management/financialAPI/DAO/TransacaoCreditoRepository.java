package management.financialAPI.DAO;

import management.financialAPI.Transacoes.TransacaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoCreditoRepository extends JpaRepository<TransacaoCredito, Long> {
    // adicionar métodos
}
