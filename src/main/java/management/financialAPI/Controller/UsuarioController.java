package management.financialAPI.Controller;

import management.financialAPI.CadastroLogin.Usuario;
import management.financialAPI.DAO.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Usuario dadosUsuario) {
        // Verifica se o e-mail ou CPF foi informado
        if ((dadosUsuario.getEmail() == null || dadosUsuario.getEmail().isEmpty()) &&
                (dadosUsuario.getCpf() == null || dadosUsuario.getCpf().isEmpty())) {
            return ResponseEntity.badRequest().body("E-mail ou CPF deve ser informado.");
        }

        // Busca o usuário por e-mail ou CPF
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailOrCpf(dadosUsuario.getEmail(), dadosUsuario.getCpf());

        // Se usuário for encontrado por e-mail ou CPF
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Verifica se a senha está correta
            if (usuario.getSenha().equals(dadosUsuario.getSenha())) {
                return ResponseEntity.ok("Login realizado com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Senha incorreta.");
            }
        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado com esse e-mail ou CPF.");
        }
    }
}
