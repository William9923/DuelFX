package com.avatarduel.exception.ExceptionCause;

/**
 * Defines the cause of an exception
 */

public interface ExceptionCause {

    /**
     * get the cause of the exception
     * @return String that describe the exception
     */
    public String getCause();
}
