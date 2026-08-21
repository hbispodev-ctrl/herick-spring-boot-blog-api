package br.edu.infnet.herick_bispo_blog_API.exception;

public class IdentificadorDuplicadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IdentificadorDuplicadoException(String message) {
        super(message);
    }
}
