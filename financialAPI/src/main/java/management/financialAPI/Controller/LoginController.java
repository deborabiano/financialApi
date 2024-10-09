package management.financialAPI.Controller;

import management.financialAPI.CadastroLogin.DadosLogin;
import management.financialAPI.CadastroLogin.Usuario;
import management.financialAPI.DAO.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody DadosLogin dadosLogin) {
        Optional<Usuario> usuarioOptional;

        // Verifica se o e-mail ou CPF existe
        if (dadosLogin.getEmail() != null && !dadosLogin.getEmail().isEmpty()) {
            usuarioOptional = usuarioRepository.findByEmail(dadosLogin.getEmail());
        } else if (dadosLogin.getCpf() != null && !dadosLogin.getCpf().isEmpty()) {
            usuarioOptional = usuarioRepository.findByCpf(dadosLogin.getCpf());
        } else {
            return ResponseEntity.badRequest().body("E-mail ou CPF deve ser informado.");
        }

        // Se usuário for encontrado por e-mail ou CPF
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Verifica se a senha está correta
            if (usuario.getSenha().equals(dadosLogin.getSenha())) {
                return ResponseEntity.ok("Login realizado com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Senha incorreta.");
            }
        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado com esse e-mail ou CPF.");
        }
    }
}
