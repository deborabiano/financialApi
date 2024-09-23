package management.financialAPI.Controller;

import jakarta.transaction.Transactional;
import management.financialAPI.CadastroLogin.Cadastro;
import management.financialAPI.DAO.CadastroRepository;
import management.financialAPI.CadastroLogin.DadosCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class cadastroController {

    @Autowired
    private CadastroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastro(@RequestBody DadosCadastro dadosCadastro) {
        repository.save(new Cadastro(dadosCadastro));
        return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }
}
