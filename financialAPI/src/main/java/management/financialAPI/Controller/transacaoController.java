package management.financialAPI.controller;

import management.financialAPI.repository.transacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class transacaoController {

    @Autowired
    private transacaoRepository repository;

    public static void setDataTransacao(LocalDateTime now) {
    }
}