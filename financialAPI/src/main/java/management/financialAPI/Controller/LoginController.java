package management.financialAPI.Controller;

import management.financialAPI.DAO.UsuarioRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginController(UsuarioRepository ignoredUsuarioRepository) {
    }

    public static LoginController createLoginController(UsuarioRepository ignoredUsuarioRepository) {
        return new LoginController(ignoredUsuarioRepository);
    }
}