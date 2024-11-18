package com.financial.api.demo.entidades.usuariosCadastro;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValidaExcelPlanilha {

    // Lista dos nomes das colunas esperadas na planilha
    private static final String[] CABECALHO_ESPERADO = {
            "NOME COMPLETO", "CPF", "DATA NASCIMENTO", "EMAIL", "TELEFONE",
            "LOGRADOURO", "NUMERO", "BAIRRO", "CEP", "CIDADE",
            "COMPLEMENTO", "UF", "SENHA", "ATIVO"
    };

    private List<String> mensagensErro = new ArrayList<>();
    private boolean valido;

    // Construtor que realiza a validação no momento da criação
    public ValidaExcelPlanilha(MultipartFile file) {
        this.valido = validarArquivoExcel(file);
    }

    // Metodo para validar o arquivo Excel
    private boolean validarArquivoExcel(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            mensagensErro.add("O arquivo está vazio.");
            return false;
        }

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Obtém a primeira aba da planilha

            // Verifica se a planilha possui linhas
            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) {
                mensagensErro.add("A planilha está vazia.");
                return false;
            }

            // Verifica o cabeçalho
            Row headerRow = rowIterator.next(); // Primeira linha é o cabeçalho
            if (!isHeaderValid(headerRow)) {
                mensagensErro.add("O cabeçalho da planilha está incorreto.");
                return false;
            }

            // Verifica se há pelo menos uma linha válida
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (!isRowEmpty(row)) {
                    return true; // Há pelo menos uma linha válida
                }
            }

            mensagensErro.add("Nenhuma linha gravada com dados válidos na planilha.");
            return false;

        } catch (Exception e) {
            mensagensErro.add("Erro ao processar o arquivo: " + e.getMessage());
            return false;
        }
    }

    // Metodo auxiliar para validar o cabeçalho
    private boolean isHeaderValid(Row headerRow) {
        if (headerRow == null) {
            mensagensErro.add("A linha de cabeçalho está ausente.");
            return false;
        }

        for (int i = 0; i < CABECALHO_ESPERADO.length; i++) {
            Cell cell = headerRow.getCell(i);
            if (cell == null || cell.getCellType() != CellType.STRING) {
                mensagensErro.add("Cabeçalho ausente ou inválido na coluna: " + (i + 1));
                return false;
            }

            String headerValue = cell.getStringCellValue().trim();
            if (!headerValue.equalsIgnoreCase(CABECALHO_ESPERADO[i])) {
                mensagensErro.add("Cabeçalho esperado: " + CABECALHO_ESPERADO[i] + ", encontrado: " + headerValue);
                return false;
            }
        }
        return true;
    }

    // Metodo auxiliar para verificar se uma linha está vazia
    private boolean isRowEmpty(Row row) {
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // Se qualquer célula da linha não estiver em branco, a linha não está vazia
            }
        }
        return true;
    }

    // Métodos de acesso
    public boolean isValido() {
        return valido;
    }

    public List<String> getMensagensErro() {
        return mensagensErro;
    }
}
