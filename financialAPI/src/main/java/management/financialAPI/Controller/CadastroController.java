package management.financialAPI.Controller;


import management.financialAPI.CadastroLogin.DadosCadastro;
import management.financialAPI.CadastroLogin.DadosLogin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/cadastro")


public class CadastroController {
    public void cadastro(@RequestBody DadosCadastro dadosCadastro) {
        System.out.println(dadosCadastro);

    }
}
