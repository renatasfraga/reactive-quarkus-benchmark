package edu.fraga.quarkus.exception;

public class InvalidInvestmentException extends RuntimeException {

    public InvalidInvestmentException(final String message) {
        super(message);
    }
}
