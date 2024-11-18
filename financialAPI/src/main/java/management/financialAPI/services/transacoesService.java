package management.financialAPI.services;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import management.financialAPI.repository.transacaoRepository;
import management.financialAPI.transacoes.transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/transacoes")
public class transacoesService {

    private final transacaoRepository transacaoRepository;

    public transacoesService(transacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<transacao> criarTransacao(@RequestBody @Valid transacaoRepository transacaoRepository) { // Changed method to use entity
        var transacao = new transacao();
        transacao.setDataTransacao(LocalDateTime.now());
        transacao savedTransacao = transacaoRepository.save(transacao);
        return ResponseEntity.ok(savedTransacao);
    }

    // List all credit transactions
    @GetMapping
    public List<transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }

    // Consult a credit transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<transacao> consultarTransacao(@PathVariable Long id) {
        return transacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id) {
        if (transacaoRepository.existsById(id)) { // Check if transaction exists
            transacaoRepository.deleteById(id); // Call delete in repository
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); // Return a not found response if not exists
        }
    }
}