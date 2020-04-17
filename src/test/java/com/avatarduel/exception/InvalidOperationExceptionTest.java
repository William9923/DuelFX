package com.avatarduel.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidOperationExceptionTest {
    private InvalidOperationException invalidOperationException;

    public InvalidOperationExceptionTest() {
        this.invalidOperationException = new InvalidOperationException("Playing land card", "Cannot play more than 1 land card at each turn");
    }

    @Test
    void getMessage() {
        assertEquals("Cannot play more than 1 land card at each turn", invalidOperationException.getMessage());
    }

    @Test
    void getOperation() {
        assertEquals("Playing land card", invalidOperationException.getOperation());
    }
}