package management.financialAPI.Controller;

import management.financialAPI.CadastroLogin.DadosLogin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")

public class LoginController {

    @PostMapping
    public void login(@RequestBody DadosLogin dadosLogin) {
                System.out.println(dadosLogin);
    }
}
