package management.financialAPI.Service;

import management.financialAPI.CadastroLogin.Usuario;
import management.financialAPI.DAO.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Injeção de dependências via construtor
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Buscar usuário por e-mail ou CPF
    public Optional<Usuario> buscarPorEmailOuCpf(String email, String cpf) {
        return usuarioRepository.findByEmailOrCpf(email, cpf);
    }

    // Salvar um usuário, garantindo que não seja nulo
    public Usuario salvar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo");
        }
        return usuarioRepository.save(usuario);
    }

    // Buscar usuários que possuem cadastro
    public List<Usuario> buscaUsuario(String email, String cpf) {
        return usuarioRepository.buscaUsuario(email, cpf);
    }
}
