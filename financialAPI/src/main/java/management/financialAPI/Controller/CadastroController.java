package management.financialAPI.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import management.financialAPI.CadastroLogin.Cadastro;
import management.financialAPI.CadastroLogin.DadosAtualizacaoCadastro;
import management.financialAPI.DAO.CadastroRepository;
import management.financialAPI.CadastroLogin.DadosCadastro;
import management.financialAPI.CadastroLogin.DadosListagemUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro")
public class cadastroController {

    @Autowired
    private CadastroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastro (@RequestBody @Valid DadosCadastro dadosCadastro) {
        repository.save(new Cadastro(dadosCadastro));
        return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }

    @GetMapping
    public Page<DadosListagemUsuarios> listar(@PageableDefault(size=20, sort = {"ativo"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuarios::new);
    }

    @PutMapping
    @Transactional
    public void atualizar (@RequestBody @Valid DadosAtualizacaoCadastro dados){
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarDados(dados);
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
    }
}
