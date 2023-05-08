package br.com.zaniboni.msavaliadorcredito.exceptions;

public class ErroSolicitacaoCartaoException extends RuntimeException{
    public ErroSolicitacaoCartaoException(String message) {
        super(message);
    }
}
