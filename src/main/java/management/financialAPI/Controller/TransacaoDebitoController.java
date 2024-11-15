package management.financialAPI.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import management.financialAPI.DAO.TransacaoDebitoRepository;
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

    // Listar todas as transações de débito
    @GetMapping
    public List<TransacaoDebito> listarTransacoes() {
        return transacaoDebitoRepository.findAll();
    }
}
