package management.financialAPI.Controller;

import management.financialAPI.DAO.TransacaoCreditoRepository;
import management.financialAPI.Transacoes.TransacaoCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transacoes/credito")
public class TransacaoCreditoController {

    @Autowired
    private TransacaoCreditoRepository repository;

    // Criar uma nova transação de crédito
    @PostMapping
    @Transactional
    public ResponseEntity<TransacaoCredito> criarTransacao(@RequestBody @Valid TransacaoCredito transacaoCredito) {
        transacaoCredito.setDataTransacao(LocalDateTime.now());
        TransacaoCredito savedTransacao = repository.save(transacaoCredito);
        return ResponseEntity.ok(savedTransacao);
    }

    // Listar todas as transações de crédito
    @GetMapping
    public List<TransacaoCredito> listarTransacoes() {
        return repository.findAll();
    }

    // Consultar uma transação de crédito por ID
    @GetMapping("/{id}")
    public ResponseEntity<TransacaoCredito> consultarTransacao(@PathVariable Long id) {
        return repository.findById(id)
                .map(transacao -> ResponseEntity.ok(transacao))
                .orElse(ResponseEntity.notFound().build());
    }
    // Excluir uma transação por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id) {
        TransacaoCredito.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }
}