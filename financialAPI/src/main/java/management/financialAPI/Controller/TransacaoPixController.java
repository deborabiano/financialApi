package management.financialAPI.Controller;

import management.financialAPI.DAO.TransacaoPixRepository;
import management.financialAPI.Transacoes.TransacaoPix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transacoes/pix")
public class TransacaoPixController {

    @Autowired
    private TransacaoPixRepository repository;

    // Criar uma nova transação via PIX
    @PostMapping
    @Transactional
    public ResponseEntity<TransacaoPix> criarTransacao(@RequestBody @Valid TransacaoPix transacaoPix) {
        transacaoPix.setDataTransacao(LocalDateTime.now());
        TransacaoPix savedTransacao = repository.save(transacaoPix);
        return ResponseEntity.ok(savedTransacao);
    }

    // Listar todas as transações via PIX
    @GetMapping
    public List<TransacaoPix> listarTransacoes() {
        return repository.findAll();
    }

    // Consultar uma transação via PIX por ID
    @GetMapping("/{id}")
    public ResponseEntity<TransacaoPix> consultarTransacao(@PathVariable Long id) {
        return repository.findById(id)
                .map(transacao -> ResponseEntity.ok(transacao))
                .orElse(ResponseEntity.notFound().build());
    }
}
