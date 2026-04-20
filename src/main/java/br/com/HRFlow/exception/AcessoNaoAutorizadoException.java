package br.com.HRFlow.exception;

public class AcessoNaoAutorizadoException extends RuntimeException {
    public AcessoNaoAutorizadoException(String message) {
        super(message);
    }
}
