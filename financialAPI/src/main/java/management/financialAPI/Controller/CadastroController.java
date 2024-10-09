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
import java.util.Optional;

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

    // Consultar cadastro por ID
    @GetMapping("/{id}")
    public ResponseEntity<DadosCadastro> consultar(@PathVariable Long id) {
        Optional<Cadastro> cadastroOpt = repository.findById(id);
        if (cadastroOpt.isPresent()) {
            Cadastro cadastro = cadastroOpt.get();
            DadosCadastro dadosCadastro = new DadosCadastro(cadastro);
            return ResponseEntity.ok(dadosCadastro);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    //Cadastro em massa - Upload Excel
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        // Verifica se o arquivo possui extensão ".xlsx"
        if (fileName == null || !fileName.endsWith(".xlsx")) {
            return ResponseEntity.badRequest().body("Formato de arquivo inválido. Apenas arquivos .xlsx são permitidos.");
        }

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