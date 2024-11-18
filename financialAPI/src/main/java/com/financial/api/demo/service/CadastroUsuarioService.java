package com.financial.api.demo.service;

import com.financial.api.demo.entidades.usuariosCadastro.DadosCadastroUsuario;
import com.financial.api.demo.tratarExceptions.CpfDuplicado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import com.financial.api.demo.repository.CadastroUsuarioRepository;

@Service
public class CadastroUsuarioService {

    @Autowired
    private CadastroUsuarioRepository cadastroRepository;

    @PostMapping("/cadastrar")
    // Metodo para criar um cadastro de usuário
    public DadosCadastroUsuario criarCadastroUsuario(DadosCadastroUsuario usuario) {
        return cadastroRepository.save(usuario);
    }

    public DadosCadastroUsuario criarCadastro(DadosCadastroUsuario cadastro) {
        // Verifica se o CPF já está cadastrado
        if (cadastroRepository.existsByCpf(cadastro.getCpf())) {
            throw new CpfDuplicado("O CPF " + cadastro.getCpf() + " já está cadastrado.");
        }

        return cadastroRepository.save(cadastro);
    }
}
