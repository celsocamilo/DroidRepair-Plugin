package com.ufg.i4soft.droidrepair.excecoes;

public class FalhaEsperaException extends RuntimeException {

    public FalhaEsperaException(final String campo) {
        super(campo);
    }
}
