package com.financial.api.demo.controller;

import com.financial.api.demo.entidades.usuariosCadastro.AtualizarUsuario;
import com.financial.api.demo.entidades.usuariosCadastro.DadosCadastroUsuario;
import com.financial.api.demo.service.ExcelUploadService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.financial.api.demo.repository.CadastroUsuarioRepository;
import com.financial.api.demo.service.CadastroUsuarioService;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class CadastroUsuarioController {

    @Autowired
    private CadastroUsuarioRepository repository;

    @Autowired
    private ExcelUploadService excelService;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    // Criar novo usuário
    @PostMapping("/cadastro")
    public DadosCadastroUsuario criarCadastro(@RequestBody DadosCadastroUsuario usuario) {
        return cadastroUsuarioService.criarCadastroUsuario(usuario);
    }

    // Consultar cadastro por ID
    @GetMapping("/{id}")
    public ResponseEntity<DadosCadastroUsuario> consultar(@PathVariable Long id) {
        Optional<DadosCadastroUsuario> cadastroOpt = repository.findById(id);
        return cadastroOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar cadastros de usuários
    @GetMapping
    public ResponseEntity<Page<DadosCadastroUsuario>> listarUsuarios(Pageable paginacao) {
        Page<DadosCadastroUsuario> usuarios = repository.findAllByAtivoTrue(paginacao);

        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    // Atualizar cadastro
    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizarUsuario dados) {
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


}