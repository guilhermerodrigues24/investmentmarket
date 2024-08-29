package com.backend.projetointegrador.services.exceptions;

import java.io.Serial;

public class InvalidArgsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidArgsException(Class entityClass, String message) {
        super("Invalid argument for " + entityClass.getSimpleName() + ". " + message);
    }
}
