package management.financialAPI.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import management.financialAPI.DAO.TransacaoDebitoRepository;
import management.financialAPI.Transacoes.TransacaoCredito;
import management.financialAPI.Transacoes.TransacaoDebito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transacoes/debito")
public class TransacaoDebitoController {

    @Autowired
    private TransacaoDebitoRepository transacaoDebitoRepository;

    // Criar uma nova transação de débito
    @PostMapping
    @Transactional
    public ResponseEntity<String> criarTransacao(@RequestBody @Valid TransacaoDebito transacaoDebito) {
        transacaoDebito.setData(LocalDateTime.now()); // Define a data da transação
        transacaoDebitoRepository.save(transacaoDebito);
        return ResponseEntity.ok("Transação de realizada com sucesso!");
    }

    // Listar todas as transações de crédito
    @GetMapping
    public List<TransacaoDebito> listarTransacoes() {
        return transacaoDebitoRepository.findAll();
    }

    // Consultar uma transação de crédito por ID
    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDebito> consultarTransacao(@PathVariable Long id) {
        return transacaoDebitoRepository.findById(id)
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
