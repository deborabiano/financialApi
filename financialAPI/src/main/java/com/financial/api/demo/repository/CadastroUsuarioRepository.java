package com.financial.api.demo.repository;

import com.financial.api.demo.entidades.usuariosCadastro.DadosCadastroUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CadastroUsuarioRepository extends JpaRepository<DadosCadastroUsuario, Long> {

    // Encontrar todos os cadastros ativos
    Page<DadosCadastroUsuario> findAllByAtivoTrue(Pageable pageable);

    // Verificar se um CPF já está cadastrado
    boolean existsByCpf(String cpf);
    default void deleteById(Long id) {

    }

    @Override
    <S extends DadosCadastroUsuario> List<S> saveAll(Iterable<S> entities);
}
