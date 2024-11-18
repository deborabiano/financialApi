package management.financialAPI.repository;

import jakarta.validation.Valid;
import management.financialAPI.transacoes.transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface transacaoRepository extends JpaRepository<transacao, Long> {

    static @Valid transacaoRepository save(@Valid transacaoRepository transacao) {

        return transacao;
    }
    }
