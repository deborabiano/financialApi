package com.financial.api.demo.service;

import com.financial.api.demo.entidades.usuariosCadastro.DadosCadastroUsuario;
import com.financial.api.demo.entidades.usuariosCadastro.EnderecoUsuario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelUploadService {

    // Metodo para ler dados de um arquivo Excel e convertê-los em uma lista de objetos DadosCadastroUsuario
    public List<DadosCadastroUsuario> lerArquivoExcel(MultipartFile file) throws IOException {
        List<DadosCadastroUsuario> dadosCadastroList = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // Ignorando a primeira linha (cabeçalho)
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    DadosCadastroUsuario dadosCadastro = criarUsuarioAPartirDaLinha(row);
                    if (dadosCadastro != null) {
                        dadosCadastroList.add(dadosCadastro);
                    }
                }
            }
        }

        return dadosCadastroList;
    }

    // Metodo auxiliar para criar um objeto DadosCadastroUsuario a partir de uma linha do Excel
    private DadosCadastroUsuario criarUsuarioAPartirDaLinha(Row row) {
        try {
            // Criar endereço do usuário
            EnderecoUsuario endereco = new EnderecoUsuario(
                    obterValorCelulaComoString(row.getCell(6)),  // Logradouro
                    obterValorCelulaComoString(row.getCell(7)),  // Número
                    obterValorCelulaComoString(row.getCell(8)),  // Bairro
                    obterValorCelulaComoString(row.getCell(9)),  // CEP
                    obterValorCelulaComoString(row.getCell(10)), // Cidade
                    obterValorCelulaComoString(row.getCell(11)), // Complemento
                    obterValorCelulaComoString(row.getCell(12))  // UF
            );

            // Criar objeto DadosCadastroUsuario
            DadosCadastroUsuario dadosCadastro = new DadosCadastroUsuario(
                    obterValorCelulaComoString(row.getCell(0)),  // Nome
                    obterValorCelulaComoString(row.getCell(1)),  // CPF
                    obterValorCelulaComoString(row.getCell(2)),  // Data de Nascimento
                    obterValorCelulaComoString(row.getCell(3)),  // Email
                    obterValorCelulaComoString(row.getCell(4)),  // Telefone
                    endereco,                                    // Endereço
                    obterValorCelulaComoString(row.getCell(5))   // Senha
            );

            dadosCadastro.setAtivo(true); // Usuário ativo por padrão
            return dadosCadastro;

        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao processar dados da linha: " + e.getMessage());
        }
    }

    // Metodo auxiliar para obter o valor da célula como String
    private String obterValorCelulaComoString(Cell cell) {
        if (cell == null) {
            return null;
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> null;
        };
    }
}
