package com.financial.api.demo.controller;

import com.financial.api.demo.entidades.usuariosCadastro.DadosCadastroUsuario;
import com.financial.api.demo.service.ExcelUploadService;
import com.financial.api.demo.repository.CadastroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class ExcelUploadController {

    @Autowired
    private ExcelUploadService excelUploadService;

    @Autowired
    private CadastroUsuarioRepository usuarioLoginRepository;

    // Endpoint para importar usuários a partir de um arquivo Excel
    @PostMapping("/import")
    public List<String> importUsersFromExcel(@RequestParam("file") MultipartFile file) {
        try {
            // Lê o arquivo Excel e converte em uma lista de objetos DadosCadastroUsuario
            List<DadosCadastroUsuario> usuarios = excelUploadService.lerArquivoExcel(file);

            // Salva os usuários no banco de dados
            usuarioLoginRepository.saveAll(usuarios);

            // Retorna feedback para o cliente
            return List.of("Importação concluída com sucesso. Total de usuários importados: " + usuarios.size());

        } catch (IOException e) {
            return List.of("Erro ao processar o arquivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return List.of("Erro ao validar dados: " + e.getMessage());
        }
    }
}
