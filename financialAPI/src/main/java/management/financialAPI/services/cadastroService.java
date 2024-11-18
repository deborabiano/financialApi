package management.financialAPI.services;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import management.financialAPI.cadastro.dadosAtualizacaoCadastro;
import management.financialAPI.cadastro.dadosCadastro;
import management.financialAPI.user.cadastroUsuario;
import management.financialAPI.errosExceptions.cpfDuplicado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import management.financialAPI.repository.cadastroUserRepository;

import java.util.List;
import java.util.Optional;


@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/cadastro")
@Service
public class cadastroService {

    @Autowired
    private cadastroUserRepository cadastroUserRepository;

    public cadastroUsuario criarCadastro(cadastroUsuario cadastroUsuario) {
        // Verifica se o CPF já está cadastrado
        if (cadastroUserRepository.existsByCpf(cadastroUsuario.getCpf())) {
            throw new cpfDuplicado("O CPF " + cadastroUsuario.getCpf() + " já está cadastrado.");
        }

        return cadastroUserRepository.save(cadastroUsuario);
    }

    // Consultar cadastro por ID
    @GetMapping("/{id}")
    public ResponseEntity<dadosCadastro> consultar(@PathVariable Long id) {
        Optional<cadastroUsuario> cadastroOpt = cadastroUserRepository.findById(id);
        if (cadastroOpt.isPresent()) {
            cadastroUsuario cadastroUsuario = cadastroOpt.get();
            dadosCadastro dadosCadastro = new dadosCadastro(cadastroUsuario);
            return ResponseEntity.ok(dadosCadastro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar cadastro
    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid dadosAtualizacaoCadastro dados) {
        var usuario = cadastroUserRepository.getReferenceById(dados.id());
        usuario.atualizarDados(dados);
        return ResponseEntity.ok("Cadastro atualizado com sucesso!");
    }

    // Excluir cadastro
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        var usuario = cadastroUserRepository.getReferenceById(id);
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

        List<dadosCadastro> cadastros = excelService.lerArquivoExcel(file);

        for (dadosCadastro dados : cadastros) {
            cadastroUsuario cadastroUsuario = new cadastroUsuario(dados);
            cadastroUserRepository.save(cadastroUsuario);
        }

        return ResponseEntity.ok("Cadastro em massa realizado com sucesso!");
    }


}
