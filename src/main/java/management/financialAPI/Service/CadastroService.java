package management.financialAPI.Service;

import management.financialAPI.CadastroLogin.Cadastro;
import management.financialAPI.ErrosExceptions.CpfDuplicado;
import management.financialAPI.DAO.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepository cadastroRepository;

    public Cadastro criarCadastro(Cadastro cadastro) {
        // Verifica se o CPF já está cadastrado
        if (cadastroRepository.existsByCpf(cadastro.getCpf())) {
            throw new CpfDuplicado("O CPF " + cadastro.getCpf() + " já está cadastrado.");
        }

        return cadastroRepository.save(cadastro);
    }
}
