package management.financialAPI.DAO;

import management.financialAPI.Transacoes.TransacaoDebito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface TransacaoDebitoRepository extends JpaRepository<TransacaoDebito, Long> {
    // adicionar métodos
}
