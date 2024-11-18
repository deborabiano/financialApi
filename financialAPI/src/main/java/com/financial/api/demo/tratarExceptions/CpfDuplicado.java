package com.financial.api.demo.tratarExceptions;

public class CpfDuplicado extends RuntimeException {
    public CpfDuplicado(String message) {
        super(message);
    }
}
