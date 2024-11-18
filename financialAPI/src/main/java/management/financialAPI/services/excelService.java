package management.financialAPI.services;

import management.financialAPI.cadastro.dadosCadastro;
import management.financialAPI.user.usuarioLogin;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class excelService {

    @Autowired
    private management.financialAPI.repository.usuarioRepository usuarioRepository;

    // Método para importar usuários a partir de um arquivo Excel
    public List<String> importUsersFromExcel(MultipartFile file) {
        List<usuarioLogin> usuarioLogins = new ArrayList<>();
        List<String> feedback = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {  // Começando da segunda linha
                Row row = sheet.getRow(i);

                try {
                    usuarioLogin usuarioLogin = criarUsuarioAPartirDaLinha(row);
                    if (usuarioLogin != null) {
                        usuarioLogins.add(usuarioLogin);
                    } else {
                        feedback.add("Linha " + (i + 1) + ": Dados incompletos ou inválidos.");
                    }
                } catch (IllegalArgumentException e) {
                    feedback.add("Linha " + (i + 1) + ": " + e.getMessage());
                }
            }
            usuarioRepository.saveAll(usuarioLogins);
            feedback.add("Importação concluída com sucesso. Total de usuários importados: " + usuarioLogins.size());
        } catch (IOException e) {
            feedback.add("Erro ao processar o arquivo: " + e.getMessage());
        }
        return feedback;
    }

    // Método auxiliar para criar um usuário a partir de uma linha do Excel
    private usuarioLogin criarUsuarioAPartirDaLinha(Row row) {
        usuarioLogin usuarioLogin = new usuarioLogin();

        try {
            String nome = obterValorCelulaComoString(row.getCell(0));
            String email = obterValorCelulaComoString(row.getCell(1));
            String senha = obterValorCelulaComoString(row.getCell(2));

            if (nome == null || email == null || senha == null) {
                throw new IllegalArgumentException("Campos obrigatórios ausentes.");
            }

            usuarioLogin.setNome(nome);
            usuarioLogin.setEmail(email);
            usuarioLogin.setSenha(senha);
            return usuarioLogin;
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao processar dados da linha.");
        }
    }

    // Método auxiliar para obter o valor da célula como string
    private String obterValorCelulaComoString(Cell cell) {
        if (cell == null) {
            return null;
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());  // Para números inteiros
            default -> null;
        };
    }

    public static List<dadosCadastro> lerArquivoExcel(MultipartFile file) {
        return List.of();
    }
}
