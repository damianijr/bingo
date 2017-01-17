package com.bingo.core.exceptions;

/**
 * Created by damianijr on 1/8/17.
 */
public class NoMoreBallsException extends RuntimeException {

    public NoMoreBallsException() {
        super("Todas os numeros do bingo foram sorteados.");
    }
}
