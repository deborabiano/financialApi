package com.financial.api.demo.repository;

import com.financial.api.demo.entidades.transacoesBancarias.TransacoesBancarias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacoesRepository extends JpaRepository<TransacoesBancarias, Long> {

}
