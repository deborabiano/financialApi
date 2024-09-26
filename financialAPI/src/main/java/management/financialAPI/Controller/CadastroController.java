package management.financialAPI.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import management.financialAPI.CadastroLogin.Cadastro;
import management.financialAPI.CadastroLogin.DadosAtualizacaoCadastro;
import management.financialAPI.DAO.CadastroRepository;
import management.financialAPI.CadastroLogin.DadosCadastro;
import management.financialAPI.CadastroLogin.DadosListagemUsuarios;
import management.financialAPI.Service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

    @RestController
    @RequestMapping("/cadastro")
    public class cadastroController {

    @Autowired
    private CadastroRepository repository;

    @Autowired
    private ExcelService excelService;

    // Cadastro individual
    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastro(@RequestBody @Valid DadosCadastro dadosCadastro) {
        repository.save(new Cadastro(dadosCadastro));
        return ResponseEntity.ok("Cadastro individual realizado com sucesso!");
    }

    // Listar cadastros
    @GetMapping
    public Page<DadosListagemUsuarios> listar(@PageableDefault(size = 20, sort = {"ativo"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuarios::new);
    }

    // Atualizar cadastro
    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid DadosAtualizacaoCadastro dados) {
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarDados(dados);
        return ResponseEntity.ok("Cadastro atualizado com sucesso!");
    }

    // Excluir cadastro
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.ok("Cadastro excluído com sucesso!");
    }

    @PostMapping("/cadastro/upload-excel")
    @Transactional
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<DadosCadastro> cadastros = excelService.lerArquivoExcel(file);

            for (DadosCadastro dados : cadastros) {
                Cadastro cadastro = new Cadastro(dados);
                repository.save(cadastro);
            }

            return ResponseEntity.ok("Cadastro em massa realizado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erro ao processar o arquivo Excel.");
        }
    }
}