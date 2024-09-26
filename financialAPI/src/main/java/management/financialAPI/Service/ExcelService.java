package management.financialAPI.Service;

import management.financialAPI.CadastroLogin.DadosCadastro;
import management.financialAPI.CadastroLogin.DadosEndereco;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    public List<DadosCadastro> lerArquivoExcel(MultipartFile file) throws IOException {
        List<DadosCadastro> cadastros = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Considerando que o arquivo tem uma única aba

        for (Row row : sheet) {
            if (row.getRowNum() == 0) { // Ignorar a linha de cabeçalho
                continue;
            }

            // Tratamento para campos que podem estar vazios ou nulos
            String nome = getCellValue(row.getCell(0));
            String cpf = getCellValue(row.getCell(1));
            String dataNascimento = getCellValue(row.getCell(2));
            String email = getCellValue(row.getCell(3));
            String telefone = getCellValue(row.getCell(4));


            DadosEndereco endereco = null;

            DadosCadastro dadosCadastro = new DadosCadastro(nome, cpf, dataNascimento, email, telefone, endereco);
            cadastros.add(dadosCadastro);
        }

        workbook.close();
        return cadastros;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:

                if (DateUtil.isCellDateFormatted(cell)) {

                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }
}
